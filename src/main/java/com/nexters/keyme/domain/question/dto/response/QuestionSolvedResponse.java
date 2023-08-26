package com.nexters.keyme.domain.question.dto.response;

import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@ApiModel(description = "푼 Question 정보 응답객체")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class QuestionSolvedResponse extends QuestionResponse {
    private int score;

    public QuestionSolvedResponse(QuestionSolved questionSolved) {
        super(
            questionSolved.getQuestion().getQuestionId(),
            questionSolved.getQuestion().getTitle(),
            questionSolved.getQuestion().getKeyword(),
            new QuestionCategoryResponse(questionSolved.getQuestion().getCategoryName())
        );
        this.score = questionSolved.getScore();
    }
}