package com.nexters.keyme.domain.statistics.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StatisticResultResponse {
    @ApiModelProperty(value="문제별 통계 정보")
    private final StatisticQuestionResponse questionStatistic;
    @ApiModelProperty(value="포도송이 좌표값 정보")
    private final CoordinateResponse coordinate;
}
