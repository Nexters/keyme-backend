package com.nexters.keyme.auth.util;

import com.nexters.keyme.auth.config.filter.JwtAuthenticationToken;
import io.jsonwebtoken.Claims;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AuthenticationTokenProvider {
  public Authentication getAuthenticationToken(Claims claims) {
    Long memberId = claims.get("memberId", Long.class);
    List<GrantedAuthority> authorities  = Arrays.stream(claims.get("role", String.class).split(","))
             .map(SimpleGrantedAuthority::new)
             .collect(Collectors.toList());

    System.out.println(authorities.get(0).toString());
    System.out.println(memberId);

    return new JwtAuthenticationToken(memberId, "", authorities);
  }
}
