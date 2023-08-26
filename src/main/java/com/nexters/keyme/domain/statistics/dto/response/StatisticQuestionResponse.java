package com.nexters.keyme.domain.statistics.dto.response;

import com.nexters.keyme.domain.question.dto.internal.QuestionStatisticInfo;
import com.nexters.keyme.domain.question.domain.model.Question;
import com.nexters.keyme.domain.question.dto.response.QuestionCategoryResponse;
import com.nexters.keyme.domain.question.dto.response.QuestionResponse;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Question 통계 정보")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticQuestionResponse extends QuestionResponse {
    private double avgScore;
    private int ownerScore;

    public StatisticQuestionResponse(QuestionStatisticInfo info) {
        super(info.getQuestionId(), info.getTitle(), info.getKeyword(), new QuestionCategoryResponse(info.getCategoryName()));
        this.avgScore = info.getAvgScore();
    }

    // 아무도 푼 사람이 없을때 통계값
    public StatisticQuestionResponse(Question question) {
        super(question);
        this.avgScore = 0.0;
    }

    public StatisticQuestionResponse(Question question, int ownerScore, double averageScore) {
        super(question);
        this.ownerScore = ownerScore;
        this.avgScore = averageScore;
    }
}
