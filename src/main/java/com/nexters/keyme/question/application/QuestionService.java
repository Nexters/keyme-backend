package com.nexters.keyme.question.application;

import com.nexters.keyme.common.dto.response.PageResponse;
import com.nexters.keyme.question.domain.model.QuestionSolved;
import com.nexters.keyme.question.presentation.dto.request.QuestionSolvedListRequest;
import com.nexters.keyme.question.presentation.dto.request.QuestionSolvedRequest;
import com.nexters.keyme.question.presentation.dto.request.QuestionStatisticRequest;
import com.nexters.keyme.question.presentation.dto.response.*;

public interface QuestionService {
    QuestionResponse getQuestion(Long questionId);
    QuestionScoreResponse getQuestionSolvedScore(Long questionId, QuestionSolvedRequest request);
    QuestionStatisticResponse getQuestionStatistic(Long questionId, QuestionStatisticRequest request);
    PageResponse<QuestionSolved> getQuestionSolvedList(Long questionId, QuestionSolvedListRequest request);
}