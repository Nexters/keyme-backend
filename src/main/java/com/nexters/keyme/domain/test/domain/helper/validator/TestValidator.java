package com.nexters.keyme.domain.test.domain.helper.validator;

import com.nexters.keyme.domain.test.domain.exceptions.NotFoundTestException;
import com.nexters.keyme.domain.test.domain.model.Test;
import com.nexters.keyme.domain.test.domain.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestValidator {
    private final TestRepository testRepository;

    public Test validateTest(Long testId) {
        return testRepository.findById(testId).orElseThrow(NotFoundTestException::new);
    }
}
