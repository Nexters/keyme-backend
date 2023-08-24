package com.nexters.keyme.domain.test.domain.helper.validator;

import com.nexters.keyme.domain.test.domain.exceptions.NotFoundTestResultException;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import com.nexters.keyme.domain.test.domain.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestResultValidator {
    private final TestResultRepository testResultRepository;

    public TestResult validateTestResult(Long testResultId) {
        return testResultRepository.findById(testResultId)
                .orElseThrow(NotFoundTestResultException::new);
    }
}
