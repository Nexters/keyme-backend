package com.nexters.keyme.statistics.presentation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public class AdditionalStatisticResponse {
    @ApiModelProperty(value="통계 정보 id", example = "1")
    private final long statisticId;
    @ApiModelProperty(value="문제 키워드", example = "소통왕")
    private final String keyword;
    @ApiModelProperty(value="푼 사람들의 평균 점수", example = "1.5")
    private final double solverAvgScore;
}
