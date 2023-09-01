package com.nexters.keyme.domain.question.dto.response;

import com.nexters.keyme.domain.question.dto.internal.QuestionInfo;
import com.nexters.keyme.domain.question.domain.model.Question;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@ApiModel(description = "Question 정보 응답객체")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class QuestionResponse {
    private Long questionId;
    @ApiModelProperty(value = "질문 내용", example = "불의를 보면 참지 않는다")
    private String title;

    @ApiModelProperty(value = "질문 내용을 한 단어로 축약 표현", example = "참군인")
    private String keyword;

    @ApiModelProperty(value = "질문의 가테고리", example = "영웅적 면모")
    private QuestionCategoryResponse category;

    public QuestionResponse(QuestionInfo questionInfo) {
        this.questionId = questionInfo.getQuestionId();
        this.title = questionInfo.getTitle();
        this.keyword = questionInfo.getKeyword();
        this.category = questionInfo.getCategory();
    }

    public QuestionResponse(Question question) {
        this.questionId = question.getQuestionId();
        this.title = question.getTitle();
        this.keyword = question.getKeyword();
        this.category = new QuestionCategoryResponse(question.getQuestionCategory());
    }
}
