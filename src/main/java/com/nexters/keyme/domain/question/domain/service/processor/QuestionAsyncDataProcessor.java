package com.nexters.keyme.domain.question.domain.service.processor;

import com.nexters.keyme.domain.question.domain.model.Question;
import com.nexters.keyme.domain.question.domain.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class QuestionAsyncDataProcessor {
    final private QuestionRepository questionRepository;

    @Async
    public CompletableFuture<List<Question>> asyncFindAllQuestionByTestId(Long testId) {
        List<Question> questionList = questionRepository.findAllQuestionByTestId(testId);
        return CompletableFuture.completedFuture(questionList);
    }
}
