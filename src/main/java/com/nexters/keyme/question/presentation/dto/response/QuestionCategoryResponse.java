package com.nexters.keyme.question.presentation.dto.response;

import io.swagger.annotations.ApiModel;
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
    private String imageUrl;
    private String name;
    private String color;
}
