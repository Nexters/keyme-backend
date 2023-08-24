package com.nexters.keyme.domain.test.application;

import com.nexters.keyme.domain.test.presentation.dto.request.TestListRequest;
import com.nexters.keyme.domain.test.presentation.dto.request.TestSubmissionRequest;
import com.nexters.keyme.domain.test.presentation.dto.response.*;

import java.util.List;

public interface TestService {
    TestDetailResponse getDailyTest(Long memberId);
    TestDetailResponse getOnboardingTest(Long memberId);
    TestDetailResponse getSpecificTest(Long memberId, Long testId);
    SingleTestStatisticsResponse getTestStatistics(Long memberId, Long testId);
    List<TestFeedResponse> getTestList(Long memberId, TestListRequest requestInfo);
    TestSubmitResponse createTestResult(Long solvedMemberId, Long testId, TestSubmissionRequest submitInfo);
    TestResultResponse getTestResult(Long resultId);
}
