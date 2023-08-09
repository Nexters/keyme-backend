package com.nexters.keyme.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexters.keyme.common.enums.AuthRole;
import com.nexters.keyme.common.security.exception.RestAuthenticationEntryPoint;
import com.nexters.keyme.common.security.filter.AnonymousAuthenticationFilter;
import com.nexters.keyme.common.security.filter.AuthorizationExceptionFilter;
import com.nexters.keyme.common.security.filter.JwtAuthenticationFilter;
import com.nexters.keyme.common.security.provider.AuthenticationTokenProvider;
import com.nexters.keyme.common.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationTokenProvider authenticationTokenProvider;
  private final ObjectMapper objectMapper;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors().configurationSource(corsConfigurationSource())
            .and()
            .csrf().disable();

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin().disable()
            .httpBasic().disable()
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, authenticationTokenProvider), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new AnonymousAuthenticationFilter(), JwtAuthenticationFilter.class)
            .addFilterBefore(new AuthorizationExceptionFilter(objectMapper), AnonymousAuthenticationFilter.class)
            .exceptionHandling()
            .authenticationEntryPoint(new RestAuthenticationEntryPoint());

    http.authorizeRequests()
            .antMatchers(
                    "/v2/api-docs",
                    "/swagger-resources",
                    "/swagger-resources/**",
                    "/configuration/ui",
                    "/configuration/security",
                    "/swagger-ui.html",
                    "/webjars/**",
                    /* swagger v3 */
                    "/v3/api-docs/**",
                    "/swagger-ui/**")
            .permitAll()
            .antMatchers("/auth/login").permitAll()
            .antMatchers(HttpMethod.GET, "/tests/{id}").permitAll()
            .antMatchers(HttpMethod.POST, "/tests/{id}/submit").permitAll()
            .anyRequest().hasRole(AuthRole.USER.name());
    //  AccessDeniedHandler 추가 필요

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.setAllowedOriginPatterns(List.of("*"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowedMethods(Arrays.asList("HEAD", "POST", "GET", "DELETE", "PUT", "PATCH", "OPTIONS"));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
    configurationSource.registerCorsConfiguration("/**", configuration);
    return configurationSource;
  }


}
