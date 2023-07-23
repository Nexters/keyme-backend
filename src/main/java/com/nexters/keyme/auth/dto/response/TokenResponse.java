package com.nexters.keyme.auth.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponse {
  @ApiModelProperty(value="Access JWT 토큰")
  private String accessToken;
}
