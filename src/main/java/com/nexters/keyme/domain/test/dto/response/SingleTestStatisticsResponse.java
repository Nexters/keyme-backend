package com.nexters.keyme.domain.test.dto.response;

import com.nexters.keyme.domain.question.dto.response.QuestionStatisticResponse;
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
    private Double averageRate;
    private Long solvedCount;
    private List<QuestionStatisticResponse> questionsStatistics;
}
