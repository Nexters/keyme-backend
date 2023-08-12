package com.nexters.keyme.question.presentation.dto.request;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel("Question 점수 요청 객체")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionSolvedRequest {
    private Long ownerId;
}
