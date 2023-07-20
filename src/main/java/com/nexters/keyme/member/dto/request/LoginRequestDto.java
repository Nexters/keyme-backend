package com.nexters.keyme.member.dto.request;

import com.nexters.keyme.common.enums.OAuthType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@ApiModel
@Getter
public class LoginRequestDto {

  @ApiModelProperty(value = "Oauth 타입(KAKAO/APPLE)", example = "KAKAO", required = true)
  @NotNull(message = "유효하지 않는 OAuth 타입")
  private OAuthType oauthType;

  @ApiModelProperty(value = "Oauth SDK를 통해 받은 access token(kakao)/identity token(apple)", example = "(token value)", required = true)
  @NotNull
  private String token;
}
