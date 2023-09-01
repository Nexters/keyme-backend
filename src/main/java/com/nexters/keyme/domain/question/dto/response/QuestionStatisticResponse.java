package com.nexters.keyme.domain.question.dto.response;

import com.nexters.keyme.domain.question.dto.internal.QuestionStatisticInfo;
import com.nexters.keyme.domain.question.domain.model.Question;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Question 통계 정보")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionStatisticResponse extends QuestionResponse {
    private Double avgScore;

    private Integer myScore;

    public QuestionStatisticResponse(QuestionStatisticInfo info) {
        super(info.getQuestionId(), info.getTitle(), info.getKeyword(), new QuestionCategoryResponse(info.getCategory()));
        this.avgScore = info.getAvgScore();
    }
}
