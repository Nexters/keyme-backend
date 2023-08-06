package com.nexters.keyme.question.presentation.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Question 정보 응답객체")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponse {
    private Long questionId;
    private String description;
    private String keyword;
    private QuestionCategoryResponse category;
}
