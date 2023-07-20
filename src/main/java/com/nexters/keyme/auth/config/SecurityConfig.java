package com.nexters.keyme.auth.config;

import com.nexters.keyme.auth.config.filter.JwtAuthenticationFilter;
import com.nexters.keyme.auth.exception.RestAuthenticationEntryPoint;
import com.nexters.keyme.auth.util.AuthenticationTokenProvider;
import com.nexters.keyme.auth.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
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
    http.cors()
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
            .antMatchers("/hello", "/members", "/auth/login")
            .permitAll()
            .anyRequest()
            .authenticated();

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource configurationSource  = new UrlBasedCorsConfigurationSource();
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.addAllowedOriginPattern("*");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");
    configuration.setAllowCredentials(true);

    configurationSource.registerCorsConfiguration("/**", configuration);
    return configurationSource;
  }

  @Bean
  public JwtAuthenticationFilter jwtTokenAuthenticationFilter() {
    return new JwtAuthenticationFilter(jwtTokenProvider, authenticationTokenProvider);
  }
}
