package com.nexters.keyme.clienttest.application;


import com.nexters.keyme.question.application.QuestionService;
import com.nexters.keyme.question.domain.model.Question;
import com.nexters.keyme.question.domain.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ClientTestServiceImpl implements ClientTestService {

    @Override
    public void deleteIssuedOnboardingTest(Long memberId) {
        // 삭제
    }

    @Override
    public void deleteIssuedDailyTest(Long memberId) {
        // 삭제
    }
}
