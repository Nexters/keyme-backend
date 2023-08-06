package com.nexters.keyme.test.presentation.dto.response;

import com.nexters.keyme.question.presentation.dto.response.QuestionWithCoordinateResponse;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "Test 결과 응답객체")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestResultResponse {
    private Long testResultId;
    private Long testId;
    private float matchRate;
    private List<QuestionWithCoordinateResponse> results;
}
