package com.nexters.keyme.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class AppleAuthKeysResponseDto {
  private List<Key> keys;

  @Getter
  static public class Key {
    private String kty;
    private String kid;
    private String use;
    private String alg;
    private String n;
    private String e;
  }
}
