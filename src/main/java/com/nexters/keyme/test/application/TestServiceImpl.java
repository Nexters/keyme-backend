package com.nexters.keyme.test.application;

import com.nexters.keyme.test.presentation.dto.request.TestListRequest;
import com.nexters.keyme.test.presentation.dto.request.TestResultRequest;
import com.nexters.keyme.test.presentation.dto.request.TestSubmissionRequest;
import com.nexters.keyme.test.presentation.dto.response.QuestionsInTestResponse;
import com.nexters.keyme.test.presentation.dto.response.TestFeedResponse;
import com.nexters.keyme.test.presentation.dto.response.TestResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    @Override
    public QuestionsInTestResponse getDailyTest(Long memberId) {
        return null;
    }

    @Override
    public QuestionsInTestResponse getOnboardingTest() {
        return null;
    }

    @Override
    public QuestionsInTestResponse getSpecificTest(Long memberId, Long testId) {
        return null;
    }

    @Override
    public List<TestFeedResponse> getTestList(Long memberId, TestListRequest requestInfo) {
        return null;
    }

    @Override
    public TestResultResponse submitTest(Long solverId, Long testId, TestSubmissionRequest submitInfo) {
        return null;
    }

    @Override
    public TestResultRequest getTestResult(Long solverId, Long testId) {
        return null;
    }
}
