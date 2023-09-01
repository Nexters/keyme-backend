package com.nexters.keyme.domain.question.domain.service.validator;


import com.nexters.keyme.domain.question.domain.model.Question;
import com.nexters.keyme.domain.question.domain.repository.QuestionRepository;
import com.nexters.keyme.domain.question.exceptions.NotFoundQuestionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionValidator {
    private final QuestionRepository questionRepository;

    public Question validateQuestion(Long questionId) {
        return questionRepository.findById(questionId).orElseThrow(NotFoundQuestionException::new);
    }
}
