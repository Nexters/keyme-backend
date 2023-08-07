package com.nexters.keyme.test.domain.helper;

import com.nexters.keyme.common.exceptions.ResourceNotFoundException;
import com.nexters.keyme.test.domain.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestValidationHelper {

    final private TestRepository testRepository;

    public void validateTest(Long testId) {
        testRepository.findById(testId).orElseThrow(ResourceNotFoundException::new);
    }
}
