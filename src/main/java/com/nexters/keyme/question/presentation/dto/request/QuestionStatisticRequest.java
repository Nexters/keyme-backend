package com.nexters.keyme.question.presentation.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel("Question의 통계 요청 객체")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionStatisticRequest {
    private Long ownerId;
}
