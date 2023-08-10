package com.nexters.keyme.test.presentation.dto.response;

import com.nexters.keyme.question.presentation.dto.response.QuestionStatisticResponse;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "Test의 통계정보")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SingleTestStatisticsResponse {
    private float averageRate;
    private int solvedCount;
    private List<QuestionStatisticResponse> questionsStatistics;
}
