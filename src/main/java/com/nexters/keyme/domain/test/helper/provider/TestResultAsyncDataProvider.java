package com.nexters.keyme.domain.test.helper.provider;

import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.test.domain.model.Test;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import com.nexters.keyme.domain.test.domain.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestResultAsyncDataProvider {
    private final TestResultRepository testResultRepository;

    @Async
    public CompletableFuture<Optional<TestResult>> asyncFindByTestAndSolver(Test test, MemberEntity member) {
        if (member == null) return CompletableFuture.completedFuture(Optional.empty());
        Optional<TestResult> testResult = testResultRepository.findByTestAndSolver(test, member);
        return CompletableFuture.completedFuture(testResult);
    }
}
