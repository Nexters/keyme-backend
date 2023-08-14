package com.nexters.keyme.question.presentation.dto.response;

import com.nexters.keyme.question.domain.internaldto.QuestionStatisticInfo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Question 통계 정보")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionStatisticResponse extends QuestionResponse {
    private float averageScore;

    public QuestionStatisticResponse(QuestionStatisticInfo info) {
        super(info.getQuestionId(), info.getTitle(), info.getKeyword(), new QuestionCategoryResponse(info.getCategoryName()));
        this.averageScore = info.getAverageScore().floatValue();
    }
}
