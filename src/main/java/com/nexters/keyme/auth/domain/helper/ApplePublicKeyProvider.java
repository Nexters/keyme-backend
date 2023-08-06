package com.nexters.keyme.auth.domain.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexters.keyme.auth.domain.internaldto.JwtHeaderInfo;
import com.nexters.keyme.auth.presentation.dto.response.AppleAuthKeysResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

@Component
@RequiredArgsConstructor
public class ApplePublicKeyProvider {
  private final String ALG_HEADER_KEY = "alg";
  private final String KID_HEADER_KEY = "kid";
  private final int POSITIVE_SIGNUM = 1;

  private final ObjectMapper objectMapper;

  public PublicKey getPublicKey(String jwtHeader, AppleAuthKeysResponse authKeys) {
    try {
      JwtHeaderInfo jwtHeaderInfoObject = objectMapper.readValue(jwtHeader, JwtHeaderInfo.class);
      AppleAuthKeysResponse.Key publicKey = authKeys.getKeys().stream()
              .filter(key -> key.getAlg().equals(jwtHeaderInfoObject.getAlg()))
              .filter(key -> key.getKid().equals(jwtHeaderInfoObject.getKid()))
              .findAny()
              .orElseThrow();
      return createPublicKey(publicKey);
    } catch (JsonMappingException e) {
      System.out.println("jwt 역직렬화 실패");
    } catch (JsonProcessingException e) {
      System.out.println("JSON 문제");
    } catch (Exception e) {
      System.out.println("일치하는 키 없음");
    }

    return null;
  }

  private PublicKey createPublicKey(AppleAuthKeysResponse.Key publicKey) {
    byte[] n = Base64Utils.decodeFromUrlSafeString(publicKey.getN());
    byte[] e = Base64Utils.decodeFromUrlSafeString(publicKey.getE());
    RSAPublicKeySpec publicKeySpec =
            new RSAPublicKeySpec(new BigInteger(POSITIVE_SIGNUM, n), new BigInteger(POSITIVE_SIGNUM, e));

    try {
      KeyFactory keyFactory = KeyFactory.getInstance(publicKey.getKty());
      return keyFactory.generatePublic(publicKeySpec);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
      System.out.println("PublicKey 생성불가");
    }

    return null;
  }
}
