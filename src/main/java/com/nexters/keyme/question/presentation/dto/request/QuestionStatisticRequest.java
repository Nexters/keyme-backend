package com.nexters.keyme.question.presentation.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel("Question의 통계 요청 객체")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionStatisticRequest {
    private Long ownerId;
}
