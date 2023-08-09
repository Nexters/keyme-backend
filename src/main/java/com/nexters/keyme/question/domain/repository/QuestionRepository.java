package com.nexters.keyme.question.domain.repository;

import com.nexters.keyme.question.domain.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(
        "select qb.questionBundleId.question from QuestionBundle qb " +
            "where qb.questionBundleId.test.testId = :testId"
    )
    List<Question> findAllQuestionByTestId(Long testId);

    List<Question> findAllByIsOnboarding(Boolean isOnboarding);
}