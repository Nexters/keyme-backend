package com.nexters.keyme.domain.statistics.domain.repository;

import com.nexters.keyme.domain.statistics.exceptions.NotFoundStatisticsException;
import com.nexters.keyme.domain.statistics.domain.model.Statistic;
import com.nexters.keyme.domain.test.annotation.RepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RepositoryTest
@Sql("/statistic.sql")
class StatisticRepositoryTest {
    @Autowired
    private StatisticRepository statisticRepository;

    @Test
    @Transactional
    @DisplayName("통계 정보 가져오기 테스트")
    void findByOwnerIdAndQuestionIdWithLock() {
        Statistic statistic = statisticRepository.findByOwnerIdAndQuestionId(1, 2)
                .orElseThrow(NotFoundStatisticsException::new);

        assertThat(statistic.getQuestionId()).isEqualTo(2L);
        assertThat(statistic.getOwnerId()).isEqualTo(1L);
        assertThat(statistic.getMatchRate()).isEqualTo(25);
    }

    @Test
    @Transactional
    @DisplayName("일치율 낮은 순 가져오기 테스트")
    void findByMemberIdSortByMatchRateAsc() {
        List<Statistic> statistics = statisticRepository.findByMemberIdSortByMatchRateAsc(1L);
        assertThat(statistics.size()).isEqualTo(5);

        assertThat(statistics.get(0).getId()).isEqualTo(1);
        assertThat(statistics.get(1).getId()).isEqualTo(2);
        assertThat(statistics.get(2).getId()).isEqualTo(3);
        assertThat(statistics.get(3).getId()).isEqualTo(4);
        assertThat(statistics.get(4).getId()).isEqualTo(5);
    }

    @Test
    @Transactional
    @DisplayName("일치율 높은 순 가져오기 테스트")
    void findByMemberIdSortByMatchRateDesc() {
        List<Statistic> statistics = statisticRepository.findByMemberIdSortByMatchRateDesc(1L);
        assertThat(statistics.size()).isEqualTo(5);

        assertThat(statistics.get(0).getId()).isEqualTo(6);
        assertThat(statistics.get(1).getId()).isEqualTo(5);
        assertThat(statistics.get(2).getId()).isEqualTo(4);
        assertThat(statistics.get(3).getId()).isEqualTo(2);
        assertThat(statistics.get(4).getId()).isEqualTo(3);
    }

    @Test
    @Transactional
    @DisplayName("특정 id 제외 통계 가져오기 테스트")
    void findExceptIdsSortByAvgScoreTest() {
        List<Statistic> statistics = statisticRepository.findExceptIdsSortByAvgScore(1L, List.of(1L, 2L), 6, 5, 5);

        assertThat(statistics.size()).isEqualTo(3);
        assertThat(statistics.get(0).getId()).isEqualTo(3L);
        assertThat(statistics.get(statistics.size() - 1).getId()).isEqualTo(5L);
    }
}