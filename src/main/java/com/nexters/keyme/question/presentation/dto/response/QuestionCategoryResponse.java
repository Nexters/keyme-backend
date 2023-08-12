package com.nexters.keyme.question.presentation.dto.response;

import com.nexters.keyme.question.domain.enums.QuestionCategoryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Question 카테고리 정보 응답객체")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionCategoryResponse {

    @ApiModelProperty(value = "카테고리 아이콘 url")
    private String imageUrl;

    @ApiModelProperty(value = "카테고리 이름")
    private String name;

    @ApiModelProperty(value = "카테고리 그라데이션 시작 색상(헥사코드)")
    private String startColor;
    @ApiModelProperty(value = "카테고리 그라데이션 끝 색상(헥사코드)")
    private String endColor;

    public QuestionCategoryResponse(QuestionCategoryType type) {
        this.imageUrl = type.getImageUrl();
        this.name = type.name();
        this.startColor = type.getStartColor();
        this.endColor = type.getEndColor();
    }
}
