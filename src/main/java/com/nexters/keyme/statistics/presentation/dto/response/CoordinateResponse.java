package com.nexters.keyme.statistics.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CoordinateResponse {
    @ApiModelProperty(value="중심의 x값", example = "0.1213131")
    private final double x;
    @ApiModelProperty(value="중심의 y값", example = "-0.24213131")
    private final double y;
    @ApiModelProperty(value="반지름", example = "0.1213131")
    private final double r;
}
