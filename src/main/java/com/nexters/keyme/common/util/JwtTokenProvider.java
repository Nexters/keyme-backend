package com.nexters.keyme.common.util;

import com.nexters.keyme.common.enums.AuthRole;
import com.nexters.keyme.common.exceptions.AuthenticationFailedException;
import com.nexters.keyme.common.exceptions.errorcode.ErrorCode;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.PublicKey;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

  @Value("${security.jwt.secretKey}")
  private String secretKey;

  private final long EXPIRATION_MS = 30 * 24 * 60 * 60 * 1000L; // 30일

  public String createToken(Long memberId) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + EXPIRATION_MS);

    Claims claims = Jwts.claims()
            .setSubject("accessToken")
            .setIssuedAt(now)
            .setExpiration(expiryDate);
    claims.put("memberId", memberId);
    claims.put("role", AuthRole.USER.role());

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
      log.error("Invalid JWT token");
      throw new AuthenticationFailedException(ErrorCode.TOKEN_INVALID);
    } catch (ExpiredJwtException e) {
      log.error("Expired JWT token");
      throw new AuthenticationFailedException(ErrorCode.TOKEN_EXPIRED);
    } catch (UnsupportedJwtException e) {
      log.error("Unsupported JWT token");
      throw new AuthenticationFailedException(ErrorCode.TOKEN_INVALID);
    } catch (IllegalArgumentException e) {
      log.error("JWT claims string is empty.");
      throw new AuthenticationFailedException(ErrorCode.UNAUTHORIZED);
    }
  }
}
