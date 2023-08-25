package com.nexters.keyme.domain.test.presentation.dto.response;

import com.nexters.keyme.domain.question.presentation.dto.response.QuestionSolvedResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "테스트 제출자와의 일치율(MMVP에서는 없음)", example = "87.7")
    private Double matchRate;

    private List<QuestionSolvedResponse> results;
}
