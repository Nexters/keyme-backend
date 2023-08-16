package com.nexters.keyme.question.application;

import com.nexters.keyme.common.dto.response.PageResponse;
import com.nexters.keyme.question.presentation.dto.request.QuestionScoreListRequest;
import com.nexters.keyme.question.presentation.dto.request.QuestionListScoreRequest;
import com.nexters.keyme.question.presentation.dto.request.QuestionStatisticRequest;
import com.nexters.keyme.question.presentation.dto.response.*;

import java.util.List;

public interface QuestionService {
    QuestionResponse getQuestion(Long questionId);
    QuestionStatisticResponse getQuestionStatistic(Long questionId, QuestionStatisticRequest request);
    PageResponse<QuestionScoreInfoResponse> getQuestionSolvedList(Long questionId, QuestionScoreListRequest request);
    List<QuestionScoreInfoResponse> getQuestionSolvedScore(QuestionListScoreRequest request);
}