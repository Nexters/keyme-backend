package com.nexters.keyme.member.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Builder
public class MemberResponseDto {
  @ApiModelProperty(value="유저 id", example = "7")
  @NotNull
  private Long id;


  private TokenResponseDto token;
}
