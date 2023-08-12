package com.nexters.keyme.question.presentation.dto.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@ApiModel(value = "Question을 푼 사람들의 점수리스트")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class QuestionSolvedListResponse {

    @ApiModelProperty(value = "전체 count", example = "123")
    private Long count;
    private List<QuestionSolvedSimpleResponse> results;
}
