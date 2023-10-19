package com.nexters.keyme.domain.question.domain.repository;

import com.nexters.keyme.domain.question.exceptions.NotFoundQuestionException;
import com.nexters.keyme.global.common.dto.internal.PageInfo;
import com.nexters.keyme.domain.question.dto.internal.QuestionStatisticInfo;
import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import com.nexters.keyme.domain.test.annotation.RepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;


@RepositoryTest
@Sql("/question.sql")
class QuestionSolvedRepositoryTest {

    @Autowired
    private QuestionSolvedRepository questionSolvedRepository;

    @Test
    @DisplayName("해당 문제플 푼 사람들의 점수를 가져온다")
    void findQuestionSolvedList() {
        // given - data.sql 내 정의
        Long questionId = 1L;
        Long questionOwnerId = 1L;
        Long cursorId = null;
        int limit = 10;

        PageInfo<QuestionSolved> questionSolvedPageInfo = questionSolvedRepository.findQuestionSolvedList(questionId, questionOwnerId, cursorId, limit);
        List<Long> questionSolvedIdList = questionSolvedPageInfo.getResults()
                .stream()
                .map(qs -> qs.getQuestionSolvedId())
                .collect(Collectors.toList());

        assertAll(
            () -> assertThat(questionSolvedIdList.get(0)).isEqualTo(21),
            () -> assertThat(questionSolvedPageInfo.getResults().size()).isEqualTo(2),
            () -> assertThat(questionSolvedPageInfo.isHasNext()).isEqualTo(false)
        );
    }

    @Test
    @DisplayName("Test이 속해있는 모든 Quesetion들의 통계를 가져온다")
    void findAllAssociatedQuestionStatisticsByTestId() {
        // given - data.sql 내 정의

        Long testId = 1L;
        List<QuestionStatisticInfo> questionStatisticInfoList = questionSolvedRepository.findAllAssociatedQuestionStatisticsByTestId(testId);

        // then
        assertAll(
            () -> assertThat(questionStatisticInfoList.get(0).getQuestionId()).isEqualTo(1),
            () -> assertThat(questionStatisticInfoList.get(0).getAvgScore()).isEqualTo(4.0)
        );
    }


    @Nested
    @DisplayName("특정 Question의 통계를 가져오기")
    class findQuestionStatisticsByQuestionIdAndOwnerId {
        @Test
        @DisplayName("성공")
        public void success() {
            // given - data.sql 내 정의
            Long questionId = 1L;
            Long ownerId = 1L;

            // when
            QuestionStatisticInfo questionStatisticInfo = questionSolvedRepository.findQuestionStatisticsByQuestionIdAndOwnerId(questionId, ownerId);

            //then
            assertAll(
                () -> assertThat(questionStatisticInfo.getAvgScore()).isEqualTo(4.0)
            );
        }

        @Test
        @DisplayName("문제를 푼 사람이 아무도 없을때 평균 score는 null")
        public void successNobodySolvedProblem() {
            // given - data.sql 내 정의
            Long questionId = 1L;
            Long ownerId = 2L;

            // when
            QuestionStatisticInfo questionStatisticInfo = questionSolvedRepository.findQuestionStatisticsByQuestionIdAndOwnerId(questionId, ownerId);
            assertAll(
                () -> assertThat(questionStatisticInfo.getQuestionId()).isEqualTo(1),
                () -> assertThat(questionStatisticInfo.getAvgScore()).isEqualTo(null)
            );
        }
    }
}