package com.nexters.keyme.domain.auth.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponse {
  @ApiModelProperty(value="Access JWT 토큰")
  private String accessToken;
}
