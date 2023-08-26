package com.nexters.keyme.domain.statistics.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class MemberStatisticResponse {
    @ApiModelProperty(value="멤버 ID")
    private final Long memberId;
    @ApiModelProperty(value="문제별 결과 정보")
    private final List<StatisticResultResponse> results;
}
