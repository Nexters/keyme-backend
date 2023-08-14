package com.nexters.keyme.question.domain.repository;

import com.nexters.keyme.common.config.QueryDslConfig;
import com.nexters.keyme.question.domain.model.QuestionSolved;
import com.nexters.keyme.test.annotation.RepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@RepositoryTest
@Import(QueryDslConfig.class)
class QuestionSolvedRepositoryTest {

    @Autowired
    private QuestionSolvedRepository questionSolvedRepository;

    @Test
    @DisplayName("질문을 푼 사람들의 점수 리스트 받아오기")
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
}