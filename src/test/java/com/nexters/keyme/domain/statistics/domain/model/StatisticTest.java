package com.nexters.keyme.domain.statistics.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatisticTest {

    @Test
    @DisplayName("통계 정보 생성 시 기본 필드값 테스트")
    void defaultValueTest() {
        Statistic statistic = new Statistic(1L, 2L, 4);

        assertThat(statistic.getMatchRate()).isEqualTo(0);
        assertThat(statistic.getSolverCount()).isEqualTo(0);
        assertThat(statistic.getSolverAvgScore()).isEqualTo(0);
    }

    @Test
    @DisplayName("다른 유저의 통계에 점수를 추가하면 해당 정보가 반영된다")
    void addNewScoreByOtherPeopleTest() {
        Statistic statistic = new Statistic(1L, 2L, 5);

        statistic.addNewScore(2L, 1);
        statistic.addNewScore(3L, 1);
        statistic.addNewScore(4L, 1);

        assertThat(statistic.getSolverAvgScore()).isEqualTo(1);
        assertThat(statistic.getSolverCount()).isEqualTo(3);
        assertThat(statistic.getMatchRate()).isEqualTo(20);
    }

    @Test
    @DisplayName("자기 통계에 점수를 추가하면 해당 정보는 무시된다")
    void addNewScoreByMyTest() {
        Statistic statistic = new Statistic(1L, 2L, 5);

        statistic.addNewScore(1L, 1);

        assertThat(statistic.getMatchRate()).isEqualTo(0);
        assertThat(statistic.getSolverCount()).isEqualTo(0);
        assertThat(statistic.getSolverAvgScore()).isEqualTo(0);
    }
}