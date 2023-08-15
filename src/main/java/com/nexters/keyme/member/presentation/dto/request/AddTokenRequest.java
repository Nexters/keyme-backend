package com.nexters.keyme.member.presentation.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AddTokenRequest {
    @ApiModelProperty(value = "FCM 토큰", example = "(token)")
    private final String token;
}
