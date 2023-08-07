package com.nexters.keyme.test.domain.helper;

import com.nexters.keyme.test.domain.model.TestResult;
import com.nexters.keyme.test.domain.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class TestResultAsyncHelper {
    private final TestResultRepository testResultRepository;

    @Async
    public CompletableFuture<Optional<TestResult>> asyncFindByTestIdAndSolverId(Long testId, Long solvedMemberId) {
        if (solvedMemberId == null) return null;

        Optional<TestResult> testResult = testResultRepository.findByTestIdAndSolverId(testId, solvedMemberId);

        return CompletableFuture.completedFuture(testResult);
    }
}
