package com.nexters.keyme.member.presentation.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NicknameVerificationRequest {
    @NotNull
    @ApiModelProperty(value="닉네임")
    private String nickname;
}
