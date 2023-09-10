package com.nexters.keyme.domain.question.domain.repository;

import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import com.nexters.keyme.domain.question.dto.internal.QuestionStatisticInfo;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionSolvedRepository extends JpaRepository<QuestionSolved, Long>, QuestionSolvedRepositoryCustom {

    List<QuestionSolved> findAllByTestResultOrderByQuestionDesc(TestResult testResult);

    @Query(
        "select qs from QuestionSolved qs " +
            "join fetch qs.question " +
            "where qs.testResult.testResultId = :resultId"
    )
    List<QuestionSolved> findAllByTestResultIdWithQuestion(Long resultId);

    @Query(
        "select new com.nexters.keyme.domain.question.dto.internal.QuestionStatisticInfo(qs.question.questionId, qs.question.title, qs.question.keyword, qs.question.questionCategory, " +
                "avg(CASE WHEN qs.testResult.solver.id != qs.owner.id or qs.testResult.solver.id IS NULL THEN qs.score END)) " +
            "from QuestionSolved qs " +
            "where qs.testResult.test.testId = :testId " +
            "group by qs.question.questionId "
    )
    List<QuestionStatisticInfo> findAllAssociatedQuestionStatisticsByTestId(Long testId);

    @Query(
        "select new com.nexters.keyme.domain.question.dto.internal.QuestionStatisticInfo(qs.question.questionId, qs.question.title, qs.question.keyword, qs.question.questionCategory, avg(qs.score)) " +
            "from QuestionSolved qs " +
            "where qs.question.questionId = :questionId " +
                "and qs.owner.id = :ownerId " +
                "and (qs.testResult.solver.id != :ownerId or qs.testResult.solver.id IS NULL)"
    )
    QuestionStatisticInfo findQuestionStatisticsByQuestionIdAndOwnerId(Long questionId, Long ownerId);

    @Query(value = "select * from question_solved qs " +
            "join test_result ts on ts.test_result_id = qs.test_result_id " +
            "where qs.question_id in :questionIds and qs.question_owner_id = :ownerId and ts.solver_id = :solverId",
            nativeQuery = true
    )
    List<QuestionSolved> findByQuestionIdsAndOwnerIdAndSolverId(List<Long> questionIds, Long ownerId, Long solverId);

    @Query(value = "select qs.score from question_solved qs " +
            "join test_result tr on tr.test_result_id = qs.test_result_id " +
            "where qs.question_owner_id = :ownerId " +
                "and qs.question_id = :questionId " +
                "and tr.solver_id = :solverId ",
            nativeQuery = true
    )
    Optional<Integer> findSolverScoreByOwnerIdAndQuestionId(Long solverId, Long ownerId, Long questionId);
}
