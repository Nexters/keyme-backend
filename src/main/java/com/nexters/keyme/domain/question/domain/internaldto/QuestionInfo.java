package com.nexters.keyme.domain.question.domain.internaldto;

import com.nexters.keyme.domain.question.domain.model.Question;
import com.nexters.keyme.domain.question.presentation.dto.response.QuestionCategoryResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionInfo {
    private Long questionId;
    private String title;
    private String keyword;
    private QuestionCategoryResponse category;

    public QuestionInfo(Question question) {
        this.questionId = question.getQuestionId();
        this.title = question.getTitle();
        this.keyword = question.getKeyword();
        this.category = new QuestionCategoryResponse(question.getCategoryName());
    }
}
