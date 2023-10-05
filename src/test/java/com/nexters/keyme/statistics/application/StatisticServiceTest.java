package com.nexters.keyme.statistics.application;

import com.nexters.keyme.domain.question.dto.response.QuestionCategoryResponse;
import com.nexters.keyme.domain.statistics.application.StatisticService;
import com.nexters.keyme.domain.statistics.dto.internal.ScoreInfo;
import com.nexters.keyme.domain.statistics.dto.internal.StatisticInfo;
import com.nexters.keyme.domain.statistics.domain.model.Statistic;
import com.nexters.keyme.domain.statistics.dto.request.AdditionalStatisticRequest;
import com.nexters.keyme.domain.statistics.dto.response.AdditionalStatisticResponse;
import com.nexters.keyme.domain.statistics.dto.request.StatisticRequest;
import com.nexters.keyme.domain.statistics.dto.response.CoordinateResponse;
import com.nexters.keyme.domain.statistics.dto.response.MemberStatisticResponse;
import com.nexters.keyme.domain.statistics.dto.response.StatisticQuestionResponse;
import com.nexters.keyme.domain.statistics.dto.response.StatisticResultResponse;
import com.nexters.keyme.domain.statistics.exceptions.NotEnoughStatisticsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@Sql("/statistic.sql")
class StatisticServiceTest {
    @Autowired
    private StatisticService statisticService;

    @Test
    @DisplayName("통계정보 추가 통합테스트")
    void createStatistic() {
        StatisticInfo info = new StatisticInfo(2L, 1L, 5);
        Statistic statistic = statisticService.createStatistic(info);


        assertThat(statistic.getOwnerId()).isEqualTo(2L);
        assertThat(statistic.getQuestionId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("점수 정보 추가 통합테스트")
    void addNewScores() {
        ScoreInfo info = new ScoreInfo(1L, 6L, 6L, 5);
        statisticService.addNewScores(info);
    }

    @Test
    @DisplayName("유저 통계 정보 가져오기 통합테스트")
    void getMemberStatistic() {
        StatisticRequest request = new StatisticRequest(StatisticRequest.StatisticType.DIFFERENT);

        MemberStatisticResponse response = statisticService.getMemberStatistic(1L, request);
        assertThat(response.getMemberId()).isEqualTo(1L);

        List<StatisticResultResponse> results = response.getResults();
        assertThat(results.size()).isEqualTo(5);

        StatisticResultResponse firstResult = results.get(0);

        StatisticQuestionResponse question = firstResult.getQuestionStatistic();
        assertThat(question.getQuestionId()).isEqualTo(1L);
        assertThat(question.getAvgScore()).isEqualTo(1L);
    }

    @Test
    @DisplayName("유저 통계 조회 시 solverCount 0인 경우 제외 테스트")
    void getMemberStatisticWhenSolverCountIsZero() {
        StatisticRequest differentRequest = new StatisticRequest(StatisticRequest.StatisticType.DIFFERENT);
        StatisticRequest similarRequest = new StatisticRequest(StatisticRequest.StatisticType.SIMILAR);

        assertThatThrownBy(() -> statisticService.getMemberStatistic(2, differentRequest)).isInstanceOf(NotEnoughStatisticsException.class);
        assertThatThrownBy(() -> statisticService.getMemberStatistic(2, similarRequest)).isInstanceOf(NotEnoughStatisticsException.class);

    }

    @Test
    @DisplayName("유저 성격 더보기 테스트")
    void viewAdditionalStatisticTest() {
        List<AdditionalStatisticResponse> response = statisticService.getAdditionalStatistics(1L, new AdditionalStatisticRequest(5, 0L));

        assertThat(response.size()).isEqualTo(0);
    }
}