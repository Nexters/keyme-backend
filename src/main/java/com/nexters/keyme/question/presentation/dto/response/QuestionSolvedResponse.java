package com.nexters.keyme.question.presentation.dto.response;

import com.nexters.keyme.question.domain.model.QuestionSolved;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@ApiModel(value = "푼 Question 정보 응답객체")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class QuestionSolvedResponse extends QuestionResponse {
    private int score;

    public QuestionSolvedResponse(QuestionSolved questionSolved) {
        super(
            questionSolved.getQuestion().getQuestionId(),
            questionSolved.getQuestion().getDescription(),
            questionSolved.getQuestion().getKeyword(),
            new QuestionCategoryResponse(questionSolved.getQuestion().getCategoryName())
        );
        this.score = questionSolved.getScore();
    }
}