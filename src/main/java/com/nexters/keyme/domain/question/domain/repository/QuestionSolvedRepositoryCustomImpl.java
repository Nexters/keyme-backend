package com.nexters.keyme.domain.question.domain.repository;

import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import com.nexters.keyme.global.common.dto.internal.PageInfo;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nexters.keyme.domain.question.domain.model.QQuestionSolved.questionSolved;

@Repository
@RequiredArgsConstructor
public class QuestionSolvedRepositoryCustomImpl implements QuestionSolvedRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public PageInfo<QuestionSolved> findQuestionSolvedList(
        Long questionId,
        Long questionOwnerId,
        Long cursorId,
        int limit
    ) {
        List<QuestionSolved> questionSolvedList = jpaQueryFactory.selectFrom(questionSolved)
                .leftJoin(questionSolved.question).fetchJoin()
                .leftJoin(questionSolved.testResult).fetchJoin()
                .where(
                    equalQuestionId(questionId),
                    equalOwnerId(questionOwnerId),
                    notEqualOwnerResult(questionOwnerId),
                    lessThanQuestionSolverId(cursorId)
                )
                .orderBy(questionSolved.createdAt.desc())
                .limit(limit)
                .fetch();

        long totalCount = jpaQueryFactory.selectFrom(questionSolved)
                .leftJoin(questionSolved.question).fetchJoin()
                .leftJoin(questionSolved.testResult).fetchJoin()
                .where(
                    equalQuestionId(questionId),
                    equalOwnerId(questionOwnerId),
                    notEqualOwnerResult(questionOwnerId)
                )
                .orderBy(questionSolved.createdAt.desc())
                .limit(limit)
                .fetchCount();

        return new PageInfo(totalCount, questionSolvedList.size() == limit, questionSolvedList);
    }

    private BooleanExpression equalQuestionId(Long questionId) {
        return questionId == null ? null : questionSolved.question.questionId.eq(questionId);
    }

    private BooleanExpression equalOwnerId(Long ownerId) {
        return ownerId == null ? null : questionSolved.owner.id.eq(ownerId);
    }

    private BooleanExpression notEqualOwnerResult(Long ownerId) {
        return ownerId == null ? null : questionSolved.testResult.solver.id.coalesce(-1L).ne(ownerId);
    }

    private BooleanExpression lessThanQuestionSolverId(Long questionSolverId) {
        return questionSolverId == null || questionSolverId == 0 ? null : questionSolved.questionSolvedId.lt(questionSolverId);
    }
}
