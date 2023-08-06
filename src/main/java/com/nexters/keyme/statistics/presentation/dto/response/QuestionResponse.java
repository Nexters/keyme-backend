package com.nexters.keyme.statistics.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class QuestionResponse {
    @ApiModelProperty(value="문제 ID", example = "22")
    private final Long questionId;
    @ApiModelProperty(value="문제 설명", example = "키미는 불의를 보면 참는다.")
    private final String description;
    @ApiModelProperty(value="문제 키워드", example = "냄새꼬")
    private final String keyword;
    @ApiModelProperty(value="문제 평균점수", example = "4.2")
    private final double avgScore;
    @ApiModelProperty(value="문제 카테고리")
    private final CategoryResponse category;
}
