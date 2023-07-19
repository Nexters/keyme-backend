package com.nexters.keyme.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

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
