package com.nexters.keyme.test.application;

import com.nexters.keyme.test.presentation.dto.request.TestListRequest;
import com.nexters.keyme.test.presentation.dto.request.TestResultRequest;
import com.nexters.keyme.test.presentation.dto.request.TestSubmissionRequest;
import com.nexters.keyme.test.presentation.dto.response.QuestionsInTestResponse;
import com.nexters.keyme.test.presentation.dto.response.TestFeedResponse;
import com.nexters.keyme.test.presentation.dto.response.TestResultResponse;

import java.util.List;

public interface TestService {
    QuestionsInTestResponse getDailyTest(Long memberId);
    QuestionsInTestResponse getOnboardingTest();
    QuestionsInTestResponse getSpecificTest(Long memberId, Long testId);
    List<TestFeedResponse> getTestList(Long memberId, TestListRequest requestInfo);
    TestResultResponse submitTest(Long solverId, Long testId, TestSubmissionRequest submitInfo);
    TestResultRequest getTestResult(Long solverId, Long testId);
}
