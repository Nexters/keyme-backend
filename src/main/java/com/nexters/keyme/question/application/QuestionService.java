package com.nexters.keyme.question.application;

import com.nexters.keyme.question.presentation.dto.request.QuestionSolvedListRequest;
import com.nexters.keyme.question.presentation.dto.request.QuestionSolvedRequest;
import com.nexters.keyme.question.presentation.dto.request.QuestionStatisticRequest;
import com.nexters.keyme.question.presentation.dto.response.QuestionResponse;
import com.nexters.keyme.question.presentation.dto.response.QuestionSolvedListResponse;
import com.nexters.keyme.question.presentation.dto.response.QuestionSolvedResponse;
import com.nexters.keyme.question.presentation.dto.response.QuestionStatisticResponse;

public interface QuestionService {
    QuestionResponse getQuestion(Long questionId);
    QuestionSolvedResponse getQuestionSolvedScore(QuestionSolvedRequest request);
    QuestionStatisticResponse getQuestionStatistics(QuestionStatisticRequest request);
    QuestionSolvedListResponse getQuestionSolvedList(QuestionSolvedListRequest request);
}