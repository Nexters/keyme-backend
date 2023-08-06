package com.nexters.keyme.member.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NicknameVerificationResponse {
    @ApiModelProperty(value="닉네임 사용 가능 여부", example = "false")
    private final boolean isValid;
    @ApiModelProperty(value="사용 불가 사유", example = "사용 가능한 닉네임입니다")
    private String description;
}
