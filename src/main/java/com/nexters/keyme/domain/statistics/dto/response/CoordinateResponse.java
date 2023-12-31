package com.nexters.keyme.domain.statistics.dto.response;

import com.nexters.keyme.domain.statistics.dto.internal.CoordinateInfo;
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

    public CoordinateResponse(CoordinateInfo coordinateInfo) {
        this.x = coordinateInfo.getX();
        this.y = coordinateInfo.getY();
        this.r = coordinateInfo.getR();
    }


}
