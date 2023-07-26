package com.nexters.keyme.config;

import com.nexters.keyme.auth.filter.JwtAuthenticationFilter;
import com.nexters.keyme.auth.exception.RestAuthenticationEntryPoint;
import com.nexters.keyme.auth.util.AuthenticationTokenProvider;
import com.nexters.keyme.auth.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationTokenProvider authenticationTokenProvider;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors().configurationSource(corsConfigurationSource())
            .and()
            .csrf().disable();

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin().disable()
            .httpBasic().disable()
            .addFilterBefore(jwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
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
            .antMatchers(
                    "/hello",
                    "/auth/login"
            )
            .permitAll()
            .anyRequest()
            .authenticated();

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.addAllowedOriginPattern("*");
    configuration.addAllowedOrigin("*");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("HEAD, POST, GET, DELETE, PUT, PATCH, OPTIONS");
    configuration.setAllowCredentials(true);

    configurationSource.registerCorsConfiguration("/**", configuration);
    return configurationSource;
  }

  @Bean
  public JwtAuthenticationFilter jwtTokenAuthenticationFilter() {
    return new JwtAuthenticationFilter(jwtTokenProvider, authenticationTokenProvider);
  }
}