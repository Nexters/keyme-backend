package com.nexters.keyme.question.domain.repository;

import com.nexters.keyme.domain.question.domain.repository.QuestionSolvedRepository;
import com.nexters.keyme.global.common.dto.internal.PageInfo;
import com.nexters.keyme.domain.question.domain.enums.QuestionCategoryType;
import com.nexters.keyme.domain.question.domain.internaldto.QuestionStatisticInfo;
import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import com.nexters.keyme.test.annotation.RepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@RepositoryTest
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

        assertAll(
            () -> assertThat(questionStatisticInfoList.get(0).getQuestionId()).isEqualTo(1),
            () -> assertThat(questionStatisticInfoList.get(0).getAvgScore()).isEqualTo(2.0),
            () -> assertThat(questionStatisticInfoList.size()).isEqualTo(10)
        );
    }

    @Test
    @DisplayName("특정 Question의 통계를 가져온다.")
    void findQuestionStatisticsByQuestionIdAndOwnerId() {
        // given - data.sql 내 정의
        Long questionId = 2L;
        Long ownerId = 1L;

        QuestionStatisticInfo questionStatisticInfo = questionSolvedRepository.findQuestionStatisticsByQuestionIdAndOwnerId(questionId, ownerId).orElse(null);

        assertAll(
            () -> assertThat(questionStatisticInfo.getAvgScore()).isEqualTo(2.0),
            () -> assertThat(questionStatisticInfo.getCategoryName()).isEqualTo(QuestionCategoryType.BODY)
        );
    }
}