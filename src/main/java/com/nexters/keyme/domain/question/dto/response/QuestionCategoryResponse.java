package com.nexters.keyme.domain.question.dto.response;

import com.nexters.keyme.domain.question.domain.model.QuestionCategory;
import com.nexters.keyme.domain.question.enums.QuestionCategoryType;
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
    private String iconUrl;

    @ApiModelProperty(value = "카테고리 이름")
    private String name;

    @ApiModelProperty(value = "색상 코드")
    private String color;

//    public QuestionCategoryResponse(QuestionCategoryType type) {
//        this.iconUrl = type.getImageUrl();
//        this.name = type.name();
//        this.color = type.getColor();
//    }

    public QuestionCategoryResponse(QuestionCategory questionCategory) {
        this.iconUrl = questionCategory.getIconUrl();
        this.name = questionCategory.getName();
        this.color = questionCategory.getColor();
    }
}
