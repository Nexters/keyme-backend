package com.nexters.keyme.question.presentation.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "푼 Question 정보 응답객체")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionSolvedResponse extends QuestionResponse {
    private int score;
}