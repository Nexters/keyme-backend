package com.nexters.keyme.statistics.application;

import com.nexters.keyme.statistics.application.dto.ScoreInfo;
import com.nexters.keyme.statistics.domain.internaldto.StatisticInfo;
import com.nexters.keyme.statistics.domain.model.Statistic;
import com.nexters.keyme.statistics.presentation.dto.request.StatisticRequest;
import com.nexters.keyme.statistics.presentation.dto.response.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
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

        MemberStatisticResponse response = statisticService.getMemberStatistic(1, request);
        assertThat(response.getMemberId()).isEqualTo(1L);

        List<StatisticResultResponse> results = response.getResults();
        assertThat(results.size()).isEqualTo(5);

        StatisticResultResponse firstResult = results.get(0);

        QuestionResponse question = firstResult.getQuestion();
        assertThat(question.getQuestionId()).isEqualTo(1L);
        assertThat(question.getAvgScore()).isEqualTo(1L);
        assertThat(question.getTitle()).isEqualTo("새로운 사람들과 대화하는 것을 즐기시겠군요?");
        assertThat(question.getKeyword()).isEqualTo("대화");

        CategoryResponse category = question.getCategory();
        assertThat(category.getName()).isEqualTo("사회적_활동");
        assertThat(category.getColor()).isEqualTo("FFFFFF");
        assertThat(category.getIconUrl()).isEqualTo("https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png");

        CoordinateResponse coordinate = firstResult.getCoordinate();
        assertThat(coordinate.getX()).isEqualTo(0.2947799111389068);
        assertThat(coordinate.getY()).isEqualTo(0.6534475680919012);
        assertThat(coordinate.getR()).isEqualTo(0.28313953920146934);

    }
}