package com.nexters.keyme.common.security.filter;

import com.nexters.keyme.common.security.provider.AuthenticationTokenProvider;
import com.nexters.keyme.common.util.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.nexters.keyme.common.config.WebMvcConfig.AUTHORIZATION_HEADER;


@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

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

    // refresh token 정책 추가될 수 있음
    log.info("토큰이 유효하지 않음");
    filterChain.doFilter(request, response);
  }
}
