package com.nexters.keyme.statistics.presentation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class MemberStatisticResponse {
    private final Long memberId;
    private final List<StatisticResultResponse> results;
}
