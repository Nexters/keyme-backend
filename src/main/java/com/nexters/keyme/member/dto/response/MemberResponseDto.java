package com.nexters.keyme.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Builder
public class MemberResponseDto {
  @NotNull
  private Long id;

  private TokenResponseDto token;
}
