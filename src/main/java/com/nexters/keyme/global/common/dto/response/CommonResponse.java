package com.nexters.keyme.global.common.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class CommonResponse {
    @NotNull
    @ApiModelProperty(value = "HTTP 상태코드")
    private final int statusCode;
    @ApiModelProperty(value = "메시지")
    private final String message;


}
