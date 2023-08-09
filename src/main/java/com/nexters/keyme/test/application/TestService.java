package com.nexters.keyme.test.application;

import com.nexters.keyme.test.presentation.dto.request.TestListRequest;
import com.nexters.keyme.test.presentation.dto.request.TestSubmissionRequest;
import com.nexters.keyme.test.presentation.dto.response.*;

import java.util.List;

public interface TestService {
    TestDetailResponse getOrCreateDailyTest(Long memberId);
    TestDetailResponse getOrCreateOnboardingTest(Long memberId);
    TestDetailResponse getSpecificTest(Long memberId, Long testId);
    SingleTestStatisticsResponse getTestStatistics(Long memberId, Long testId);
    List<TestFeedResponse> getTestList(Long memberId, TestListRequest requestInfo);
    TestSubmitResponse createTestResult(Long solvedMemberId, Long testId, TestSubmissionRequest submitInfo);
    TestResultResponse getTestResult(Long testId, Long resultId);
}
