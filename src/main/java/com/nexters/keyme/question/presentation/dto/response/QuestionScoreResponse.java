package com.nexters.keyme.question.presentation.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@ApiModel(value = "Question을 풀었을 당시 점수")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionScoreResponse {
    private int score;
}
