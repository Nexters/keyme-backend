package com.nexters.keyme.question.presentation.dto.response;


import com.nexters.keyme.question.domain.model.QuestionSolved;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@ApiModel(value = "Question을 푼 사람들의 점수리스트")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class QuestionSolvedListResponse {

    @ApiModelProperty(value = "전체 count", example = "123")
    private Long totalCount;
    private List<QuestionSolvedSimpleResponse> results;

    public QuestionSolvedListResponse(Page<QuestionSolved> questionSolvedPage) {
        this.totalCount = questionSolvedPage.getTotalElements();
        this.results = questionSolvedPage.getContent().stream()
                .map(q -> new QuestionSolvedSimpleResponse(q))
                .collect(Collectors.toList());
    }

}
