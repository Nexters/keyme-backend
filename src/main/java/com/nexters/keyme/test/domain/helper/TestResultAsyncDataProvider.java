package com.nexters.keyme.test.domain.helper;

import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.test.domain.model.Test;
import com.nexters.keyme.test.domain.model.TestResult;
import com.nexters.keyme.test.domain.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class TestResultAsyncDataProvider {
    private final TestResultRepository testResultRepository;

    @Async
    public CompletableFuture<Optional<TestResult>> asyncFindByTestAndSolver(Test test, MemberEntity member) {
        if (member == null) return CompletableFuture.completedFuture(null);

        Optional<TestResult> testResult = testResultRepository.findByTestAndSolver(test, member);

        return CompletableFuture.completedFuture(testResult);
    }
}
