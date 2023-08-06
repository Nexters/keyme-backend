package com.nexters.keyme.statistics.presentation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StatisticResultResponse {
    private final QuestionResponse question;
    private final CoordinateResponse coordinate;
}
