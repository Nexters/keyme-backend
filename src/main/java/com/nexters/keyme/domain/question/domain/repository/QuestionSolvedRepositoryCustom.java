package com.nexters.keyme.domain.question.domain.repository;

import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import com.nexters.keyme.global.common.dto.internal.PageInfo;

public interface QuestionSolvedRepositoryCustom {
    PageInfo<QuestionSolved> findQuestionSolvedList(
        Long questionId,
        Long questionOwnerId,
        Long cursorId,
        int limit
    );
}
