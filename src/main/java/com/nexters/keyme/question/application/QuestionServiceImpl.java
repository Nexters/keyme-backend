package com.nexters.keyme.question.application;

import com.nexters.keyme.question.presentation.dto.request.QuestionSolvedListRequest;
import com.nexters.keyme.question.presentation.dto.request.QuestionSolvedRequest;
import com.nexters.keyme.question.presentation.dto.request.QuestionStatisticRequest;
import com.nexters.keyme.question.presentation.dto.response.QuestionResponse;
import com.nexters.keyme.question.presentation.dto.response.QuestionSolvedListResponse;
import com.nexters.keyme.question.presentation.dto.response.QuestionSolvedResponse;
import com.nexters.keyme.question.presentation.dto.response.QuestionStatisticResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {


    @Override
    public QuestionResponse getQuestion(Long questionId) {
        return null;
    }

    @Override
    public QuestionSolvedResponse getQuestionSolvedScore(QuestionSolvedRequest request) {
        return null;
    }

    @Override
    public QuestionStatisticResponse getQuestionStatistics(QuestionStatisticRequest request) {
        return null;
    }

    @Override
    public QuestionSolvedListResponse getQuestionSolvedList(QuestionSolvedListRequest request) {
        return null;
    }
}
