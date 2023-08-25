package com.nexters.keyme.domain.question.domain.repository;

import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import com.nexters.keyme.domain.question.domain.internaldto.QuestionStatisticInfo;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionSolvedRepository extends JpaRepository<QuestionSolved, Long>, QuestionSolvedRepositoryCustom {

    List<QuestionSolved> findAllByTestResultOrderByQuestion(TestResult testResult);

    @Query(
        "select qs from QuestionSolved qs " +
            "where qs.testResult.solver.id = :solverId and qs.testResult.test.member.id = :ownerId " +
            "order by qs.createdAt DESC "
    )
    List<QuestionSolved> findFirstLastestQuestionSolved(Long solverId, Long ownerId);

    @Query(
        "select qs from QuestionSolved qs " +
            "join fetch qs.question " +
            "where qs.testResult.testResultId = :resultId"
    )
    List<QuestionSolved> findAllByTestResultIdWithQuestion(Long resultId);

    @Query(
        "select new com.nexters.keyme.domain.question.domain.internaldto.QuestionStatisticInfo(qs.question.questionId, qs.question.title, qs.question.keyword, qs.question.categoryName, avg(qs.score)) " +
            "from QuestionSolved qs " +
            "where qs.testResult.test.testId = :testId " +
                "and (qs.testResult.solver.id != qs.owner.id or qs.testResult.solver.id IS NULL) " +
            "group by qs.question.questionId "
    )
    List<QuestionStatisticInfo> findAllAssociatedQuestionStatisticsByTestId(Long testId);

    @Query(
        "select new com.nexters.keyme.domain.question.domain.internaldto.QuestionStatisticInfo(qs.question.questionId, qs.question.title, qs.question.keyword, qs.question.categoryName, avg(qs.score)) " +
            "from QuestionSolved qs " +
            "where qs.question.questionId = :questionId " +
                "and qs.owner.id = :ownerId " +
                "and (qs.testResult.solver.id != :ownerId or qs.testResult.solver.id IS NULL)"
    )
    QuestionStatisticInfo findQuestionStatisticsByQuestionIdAndOwnerId(Long questionId, Long ownerId);

    @Query(value = "SELECT * FROM question_solved qs " +
            "JOIN test_result ts on ts.test_result_id = qs.test_result_id " +
            "WHERE qs.question_id IN :questionIds AND qs.question_owner_id = :ownerId AND ts.solver_id = :solverId",
            nativeQuery = true
    )
    List<QuestionSolved> findByQuestionIdsAndOwnerIdAndSolverId(List<Long> questionIds, Long ownerId, Long solverId);
}
