package com.nexters.keyme.domain.test.helper.validator;

import com.nexters.keyme.domain.test.exceptions.NotFoundTestResultException;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import com.nexters.keyme.domain.test.domain.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TestResultValidator {
    private final TestResultRepository testResultRepository;

    @Transactional(readOnly = true)
    public TestResult validateTestResult(Long testResultId) {
        return testResultRepository.findById(testResultId)
                .orElseThrow(NotFoundTestResultException::new);
    }

    @Transactional(readOnly = true)
    public TestResult validateTestResultExist() {
        // member와 Test
        return null;
    }
}
