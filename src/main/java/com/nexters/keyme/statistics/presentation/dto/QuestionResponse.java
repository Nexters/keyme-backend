package com.nexters.keyme.statistics.presentation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class QuestionResponse {
    private final Long questionId;
    private final String description;
    private final String keyword;
    private final int score;
    private final CategoryResponse category;
}
