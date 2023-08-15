package com.nexters.keyme.statistics.presentation.dto.response;

import com.nexters.keyme.question.presentation.dto.response.QuestionStatisticResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StatisticResultResponse {
    @ApiModelProperty(value="문제별 통계 정보")
    private final QuestionStatisticResponse questionStatistic;
    @ApiModelProperty(value="포도송이 좌표값 정보")
    private final CoordinateResponse coordinate;
}
