package com.nexters.keyme.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponseDto {
  private String accessToken;
  private String refreshToken;
}
