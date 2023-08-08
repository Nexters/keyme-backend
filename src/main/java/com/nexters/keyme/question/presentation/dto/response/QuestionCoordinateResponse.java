package com.nexters.keyme.question.presentation.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Question 좌표계")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionCoordinateResponse {
    private double x;
    private double y;
    private double r;
}