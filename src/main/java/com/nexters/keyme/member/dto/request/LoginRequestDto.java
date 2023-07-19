package com.nexters.keyme.member.dto.request;

import com.nexters.keyme.common.enums.OAuthType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class LoginRequestDto {

  @NotNull(message = "유효하지 않는 OAuth 타입")
  private OAuthType oauthType;

  @NotNull
  private String token;
}
