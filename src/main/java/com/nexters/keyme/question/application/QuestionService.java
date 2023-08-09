package com.nexters.keyme.question.application;

import com.nexters.keyme.question.presentation.dto.response.QuestionResponse;

public interface QuestionService {
    QuestionResponse getQuestion(Long questionId);
}
