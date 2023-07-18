package com.nexters.keyme.auth.util;

import com.nexters.keyme.auth.enums.AuthRole;
import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.security.PublicKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

  @Value("${security.jwt.secretKey}")
  private String secretKey;

  private final int EXPIRATION_MS = 172800000; // 2일

  public String createToken(Long memberId) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + EXPIRATION_MS);

    Claims claims = Jwts.claims()
            .setSubject("accessToken")
            .setIssuedAt(now)
            .setExpiration(expiryDate);
    claims.put("memberId", memberId);
    claims.put("role", AuthRole.ROLE_USER.name());

    String jwt = Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS256,  secretKey.getBytes())
            .compact();

    return jwt;
  }

  public Boolean verifyToken(String token) {
    return verifyToken(token, secretKey);
  }

  public Boolean verifyToken(String token, PublicKey key) {
    JwtParser parser = Jwts.parserBuilder()
            .setSigningKey(key)
            .build();
    Claims body = checkTokenValidation(token, parser);
    return body != null;
  }

  public Boolean verifyToken(String token, String key) {
    JwtParser parser = Jwts.parserBuilder()
            .setSigningKey(key.getBytes())
            .build();
    Claims body = checkTokenValidation(token, parser);
    return body != null;
  }

  public Claims extractClaims(String token) {
    return extractClaims(token, secretKey);
  }

  public Claims extractClaims(String token, String key) {
    JwtParser parser = Jwts.parserBuilder()
            .setSigningKey(key.getBytes())
            .build();
    return checkTokenValidation(token, parser);
  }

  public String extractJwtBodyString(String token) {
    String[] jwtParts = token.split("\\.");
    String encodedBody = jwtParts[1];
    byte[] decodedBytes = Base64.decodeBase64(encodedBody);
    return new String(decodedBytes);
  }

  public String extractJwtHeaderString(String token) {
    String[] jwtParts = token.split("\\.");
    String encodedHeader = jwtParts[0];
    byte[] decodedBytes = Base64.decodeBase64(encodedHeader);
    return new String(decodedBytes);
  }

  private Claims checkTokenValidation(String token, JwtParser parser) {
    try {
      return parser.parseClaimsJws(token).getBody();
    } catch (MalformedJwtException e) {
      System.out.println("Invalid JWT token");
    } catch (ExpiredJwtException e) {
      System.out.println("Expired JWT token");
    } catch (UnsupportedJwtException e) {
      System.out.println("Unsupported JWT token");
    } catch (IllegalArgumentException e) {
      System.out.println("JWT claims string is empty.");
    }

    return null;
  }
}
