package com.nexters.keyme.domain.statistics.dto.response;

import com.nexters.keyme.domain.question.domain.model.Question;
import com.nexters.keyme.domain.statistics.domain.model.Statistic;
import com.nexters.keyme.domain.statistics.dto.internal.CoordinateInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class StatisticResultResponse {
    @ApiModelProperty(value="문제별 통계 정보")
    private final StatisticQuestionResponse questionStatistic;
    @ApiModelProperty(value="포도송이 좌표값 정보")
    private final CoordinateResponse coordinate;

    public static List<StatisticResultResponse> CreateListFrom(List<Question> questions, List<Statistic> statistics, List<CoordinateInfo> coordinates) {
        List<StatisticResultResponse> results = new ArrayList<>();

        for (int i = 0; i < statistics.size(); i++) {
            Statistic statistic = statistics.get(i);
            CoordinateInfo coordinateInfo = coordinates.get(i);
            Question question = questions.get(i);

            results.add(new StatisticResultResponse(new StatisticQuestionResponse(question, statistic.getOwnerScore(), statistic.getSolverAvgScore()), new CoordinateResponse(coordinateInfo)));
        }

        return results;
    }
}
