package com.nexters.keyme.question.domain.helper;

import com.nexters.keyme.question.domain.model.Question;
import com.nexters.keyme.question.domain.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class QuestionAsyncDataProvider {
    final private QuestionRepository questionRepository;

    @Async
    public CompletableFuture<List<Question>> asyncFindAllQuestionByTestId(Long testId) {
        List<Question> questionList = questionRepository.findAllQuestionByTestId(testId);
        return CompletableFuture.completedFuture(questionList);
    }
}
