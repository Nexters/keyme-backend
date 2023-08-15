package com.nexters.keyme.question.presentation.dto.response;


import com.nexters.keyme.question.domain.model.QuestionSolved;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@ApiModel(description = "Question 점수 및 풀이일자")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class QuestionSolvedScoreInfoResponse {
    private Long id;
    private int score;
    private LocalDateTime createdAt;

    public QuestionSolvedScoreInfoResponse(QuestionSolved questionSolved) {
        this.id = questionSolved.getQuestionSolvedId();
        this.score = questionSolved.getScore();
        this.createdAt = questionSolved.getCreatedAt();
    }
}