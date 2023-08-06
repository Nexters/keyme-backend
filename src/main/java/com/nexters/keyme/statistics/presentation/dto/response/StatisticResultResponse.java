package com.nexters.keyme.statistics.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StatisticResultResponse {
    @ApiModelProperty(value="문제 정보")
    private final QuestionResponse question;
    @ApiModelProperty(value="포도송이 좌표값 정보")
    private final CoordinateResponse coordinate;
}
