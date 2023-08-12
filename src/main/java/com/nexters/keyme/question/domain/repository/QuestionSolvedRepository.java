package com.nexters.keyme.question.domain.repository;

import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.question.domain.internaldto.QuestionStatisticInfo;
import com.nexters.keyme.question.domain.model.Question;
import com.nexters.keyme.question.domain.model.QuestionSolved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionSolvedRepository extends JpaRepository<QuestionSolved, Long>, QuestionSolvedRepositoryCustom {

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
        "select new com.nexters.keyme.question.domain.internaldto.QuestionStatisticInfo(qs.question.questionId, qs.question.title, qs.question.keyword, qs.question.categoryName, avg(qs.score)) " +
            "from QuestionSolved qs " +
            "where qs.testResult.test.testId = :testId " +
                "and qs.testResult.solver.id != qs.owner.id " +
            "group by qs.question.questionId "
    )
    List<QuestionStatisticInfo> findAllAssociatedQuestionStatisticsByTestId(Long testId);

    @Query(
        "select new com.nexters.keyme.question.domain.internaldto.QuestionStatisticInfo(qs.question.questionId, qs.question.title, qs.question.keyword, qs.question.categoryName, avg(qs.score)) " +
            "from QuestionSolved qs " +
            "where qs.question.questionId = :questionId " +
                "and qs.owner.id = :ownerId " +
                "and qs.testResult.solver.id != :ownerId"
    )
    Optional<QuestionStatisticInfo> findQuestionStatisticsByQuestionIdAndOwnerId(Long questionId, Long ownerId);

    Optional<QuestionSolved> findByQuestionAndOwner(Question question, MemberEntity owner);
}
