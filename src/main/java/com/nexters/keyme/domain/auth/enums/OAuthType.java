package com.nexters.keyme.domain.auth.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum OAuthType {
  KAKAO("KAKAO"), APPLE("APPLE");

  private String name;

  @JsonCreator
  public static OAuthType from(String inputValue) {
    return Stream.of(OAuthType.values())
            .filter(category -> category.toString().equals(inputValue.toUpperCase()))
            .findFirst()
            .orElse(null);
  }
}
