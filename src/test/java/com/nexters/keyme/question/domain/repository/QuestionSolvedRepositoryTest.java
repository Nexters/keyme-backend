package com.nexters.keyme.question.domain.repository;

import com.nexters.keyme.common.config.QueryDslConfig;
import com.nexters.keyme.question.domain.internaldto.QuestionStatisticInfo;
import com.nexters.keyme.question.domain.model.QuestionSolved;
import com.nexters.keyme.test.annotation.RepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@RepositoryTest
@Import(QueryDslConfig.class)
class QuestionSolvedRepositoryTest {

    @Autowired
    private QuestionSolvedRepository questionSolvedRepository;

    @Test
    @DisplayName("해당 문제플 푼 사람들의 점수를 가져온다")
    void findQuestionSolvedList() {
        Long questionId = 1L;
        Long questionOwnerId = 1L;
        Long cursorId = null;
        int limit = 10;

        Page<QuestionSolved> questionSolvedList = questionSolvedRepository.findQuestionSolvedList(questionId, questionOwnerId, cursorId, limit);
        List<Long> questionSolvedIdList = questionSolvedList.getContent()
                .stream()
                .map(qs -> qs.getQuestionSolvedId())
                .collect(Collectors.toList());

        assertAll(
            () -> assertThat(questionSolvedIdList.get(0)).isEqualTo(21),
            () -> assertThat(questionSolvedList.getContent().size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("Test이 속해있는 모든 Quesetion들의 통계를 가져온다")
    void findAllAssociatedQuestionStatisticsByTestId() {
        Long testId = 1L;
        List<QuestionStatisticInfo> questionStatisticInfoList = questionSolvedRepository.findAllAssociatedQuestionStatisticsByTestId(testId);

        assertAll(
            () -> assertThat(questionStatisticInfoList.get(0).getQuestionId()).isEqualTo(1),
            () -> assertThat(questionStatisticInfoList.get(0).getAverageScore()).isEqualTo(2.0),
            () -> assertThat(questionStatisticInfoList.size()).isEqualTo(10)
        );
    }

    @Test
    @DisplayName("특정 Question의 통계를 가져온다.")
    void findQuestionStatisticsByQuestionIdAndOwnerId() {
        Long questionId = 1L;
        Long ownerId = 1L;

        QuestionStatisticInfo questionStatisticInfo = questionSolvedRepository.findQuestionStatisticsByQuestionIdAndOwnerId(questionId, ownerId).orElse(null);

        assertThat(questionStatisticInfo.getAverageScore()).isEqualTo(2.0);
    }
}