package com.nexters.keyme.member.dto.request;

import com.nexters.keyme.common.enums.OAuthType;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class LoginRequest {

  @NotNull(message = "유효하지 않는 OAuth 타입")
  private OAuthType oauthType;

  @NotNull
  private String token;
}
