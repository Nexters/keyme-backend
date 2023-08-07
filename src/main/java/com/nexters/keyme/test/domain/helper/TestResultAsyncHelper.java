package com.nexters.keyme.test.domain.helper;

import com.nexters.keyme.question.domain.repository.QuestionRepository;
import com.nexters.keyme.test.domain.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestAsyncHelper {
    private final TestResultRepository testResultRepository;

}
