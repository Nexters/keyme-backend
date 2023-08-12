package com.nexters.keyme.question.presentation.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class QuestionSolvedSimpleResponse {
    private int score;
    private LocalDateTime createdAt;
}
