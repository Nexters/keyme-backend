package com.nexters.keyme.statistics.presentation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public class AdditionalStatisticResponse {
    private final String keyword;
    private final double solverAvgScore;
}
