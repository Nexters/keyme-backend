package com.nexters.keyme.question.presentation.dto.response;

import com.nexters.keyme.question.domain.internaldto.QuestionStatisticInfo;
import com.nexters.keyme.question.domain.model.Question;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Question 통계 정보")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionStatisticResponse extends QuestionResponse {
    private double avgScore;

    public QuestionStatisticResponse(QuestionStatisticInfo info) {
        super(info.getQuestionId(), info.getTitle(), info.getKeyword(), new QuestionCategoryResponse(info.getCategoryName()));
        this.avgScore = info.getAvgScore();
    }

    // 아무도 푼 사람이 없을때 통계값
    public QuestionStatisticResponse(Question question) {
        super(question);
        this.avgScore = 0.0;
    }

    public QuestionStatisticResponse(Question question, double averageScore) {
        super(question);
        this.avgScore = averageScore;
    }
}
