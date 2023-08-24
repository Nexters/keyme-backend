package com.nexters.keyme.domain.test.helper.provider;


import com.nexters.keyme.domain.test.domain.model.TestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TestResultDataProvider {

    @Transactional
    public Float calculateTestResultMatchRate() {
        return null;
    }

    @Transactional
    public TestResult createTestResult() {
        return null;
    }
}
