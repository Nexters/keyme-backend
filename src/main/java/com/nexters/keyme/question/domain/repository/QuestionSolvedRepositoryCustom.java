package com.nexters.keyme.question.domain.repository;

import com.nexters.keyme.common.dto.internal.PageInfo;
import com.nexters.keyme.question.domain.model.QuestionSolved;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuestionSolvedRepositoryCustom {
    PageInfo<QuestionSolved> findQuestionSolvedList(
        Long questionId,
        Long questionOwnerId,
        Long cursorId,
        int limit
    );
}
