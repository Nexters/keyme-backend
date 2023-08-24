package com.nexters.keyme.global.security.filter;

import com.nexters.keyme.global.security.provider.AuthenticationTokenProvider;
import com.nexters.keyme.global.common.util.JwtTokenProvider;
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

import static com.nexters.keyme.global.common.constant.ConstantString.AUTHORIZATION_HEADER;


@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final Pattern AUTHORIZATION_PATTERN = Pattern.compile("^[Bb]earer (.*)$");

  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationTokenProvider authenticationTokenProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authorization = request.getHeader(AUTHORIZATION_HEADER);

    if (authorization == null || authorization.isEmpty()) {
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
    }

    filterChain.doFilter(request, response);
  }
}
