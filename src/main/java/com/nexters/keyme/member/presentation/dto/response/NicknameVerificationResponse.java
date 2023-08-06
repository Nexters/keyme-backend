package com.nexters.keyme.member.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NicknameVerificationResponse {
    @ApiModelProperty(value="닉네임 사용 가능 여부")
    private final boolean isValid;
    @ApiModelProperty(value="사용 불가 사유")
    private String description;
}
