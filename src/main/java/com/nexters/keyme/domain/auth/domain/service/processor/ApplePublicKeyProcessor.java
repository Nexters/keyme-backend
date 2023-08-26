package com.nexters.keyme.domain.auth.domain.service.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexters.keyme.domain.auth.exceptions.InvalidAppleKeyException;
import com.nexters.keyme.domain.auth.dto.internal.JwtHeaderInfo;
import com.nexters.keyme.domain.auth.dto.response.AppleAuthKeysResponse;
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
public class ApplePublicKeyProcessor {

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
    } catch (JsonProcessingException | NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new InvalidAppleKeyException();
    }
  }

  private PublicKey createPublicKey(AppleAuthKeysResponse.Key publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
    byte[] n = Base64Utils.decodeFromUrlSafeString(publicKey.getN());
    byte[] e = Base64Utils.decodeFromUrlSafeString(publicKey.getE());
    RSAPublicKeySpec publicKeySpec =
            new RSAPublicKeySpec(new BigInteger(POSITIVE_SIGNUM, n), new BigInteger(POSITIVE_SIGNUM, e));

    KeyFactory keyFactory = KeyFactory.getInstance(publicKey.getKty());
    return keyFactory.generatePublic(publicKeySpec);
  }
}
