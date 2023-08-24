package com.nexters.keyme.domain.question.application;

import com.nexters.keyme.domain.question.presentation.dto.response.QuestionResponse;
import com.nexters.keyme.domain.question.presentation.dto.response.QuestionScoreInfoResponse;
import com.nexters.keyme.domain.question.presentation.dto.response.QuestionStatisticResponse;
import com.nexters.keyme.global.common.dto.response.PageResponse;
import com.nexters.keyme.domain.question.presentation.dto.request.QuestionScoreListRequest;
import com.nexters.keyme.domain.question.presentation.dto.request.QuestionListScoreRequest;
import com.nexters.keyme.domain.question.presentation.dto.request.QuestionStatisticRequest;

import java.util.List;

public interface QuestionService {
    QuestionResponse getQuestion(Long questionId);
    QuestionStatisticResponse getQuestionStatistic(Long questionId, QuestionStatisticRequest request);
    PageResponse<QuestionScoreInfoResponse> getQuestionSolvedList(Long questionId, QuestionScoreListRequest request);
    List<QuestionScoreInfoResponse> getQuestionSolvedScore(QuestionListScoreRequest request);
}