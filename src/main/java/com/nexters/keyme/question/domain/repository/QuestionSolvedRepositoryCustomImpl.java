package com.nexters.keyme.question.domain.repository;

import com.nexters.keyme.question.domain.model.QQuestionSolved;
import com.nexters.keyme.question.domain.model.QuestionSolved;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nexters.keyme.question.domain.model.QQuestionSolved.questionSolved;

@Repository
@RequiredArgsConstructor
public class QuestionSolvedRepositoryCustomImpl implements QuestionSolvedRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<QuestionSolved> findQuestionSolvedList(
        Long questionId,
        Long ownerId,
        Long cursorId,
        int limit
    ) {
        List<QuestionSolved> questionSolvedList = jpaQueryFactory.selectFrom(questionSolved)
                .leftJoin(questionSolved.question).fetchJoin()
                .leftJoin(questionSolved.testResult).fetchJoin()
                .where(
                    equalQuestionId(questionId),
                    equalOwnerId(ownerId),
                    notEqualOwnerResult(ownerId),
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
                        equalOwnerId(ownerId),
                        notEqualOwnerResult(ownerId)
                )
                .orderBy(questionSolved.createdAt.desc())
                .limit(limit)
                .fetchCount();


        return new PageImpl(questionSolvedList, Pageable.unpaged(), totalCount);
    }

    private BooleanExpression equalQuestionId(Long questionId) {
        return questionId == null ? null : questionSolved.question.questionId.eq(questionId);
    }

    private BooleanExpression equalOwnerId(Long ownerId) {
        return ownerId == null ? null : questionSolved.owner.id.eq(ownerId);
    }

    private BooleanExpression notEqualOwnerResult(Long ownerId) {
        return ownerId == null ? null : questionSolved.testResult.solver.id.ne(ownerId);
    }

    private BooleanExpression lessThanQuestionSolverId(Long questionSolverId) {
        return questionSolverId == null ? null : questionSolved.questionSolvedId.lt(questionSolverId);
    }
}
