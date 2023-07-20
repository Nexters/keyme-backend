package com.nexters.keyme.member.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponseDto {
  @ApiModelProperty(value="Access JWT 토큰")
  private String accessToken;
  @ApiModelProperty(value="Refresh JWT 토큰")
  private String refreshToken;
}
