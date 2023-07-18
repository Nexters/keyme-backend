package com.nexters.keyme.auth.config.filter;

import com.nexters.keyme.auth.enums.AuthRole;
import com.nexters.keyme.auth.util.AuthenticationTokenProvider;
import com.nexters.keyme.auth.util.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  public static final String AUTHORIZATION_HEADER = "Authorization";
  private static final Pattern AUTHORIZATION_PATTERN = Pattern.compile("^[Bb]earer (.*)$");

  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationTokenProvider authenticationTokenProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authorization = request.getHeader(AUTHORIZATION_HEADER);

    if (authorization == null) {
      filterChain.doFilter(request, response);
      return;
    }

    Matcher matcher = AUTHORIZATION_PATTERN.matcher(authorization);

    if (matcher.matches()) {
      System.out.println("토큰 받음");
      String accessToken = authorization.substring(7);
      Boolean isVerifiedToken = jwtTokenProvider.verifyToken(accessToken);

      if (isVerifiedToken) {
        Claims jwtBody = jwtTokenProvider.extractClaims(accessToken);
        Authentication authenticationToken = authenticationTokenProvider.getAuthenticationToken(jwtBody);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }

      filterChain.doFilter(request, response);
      return;
    }

    // & refresh token 정책 추가될 수 있음
    System.out.println("토큰이 유효하지 않음");
    filterChain.doFilter(request, response);
  }
}
