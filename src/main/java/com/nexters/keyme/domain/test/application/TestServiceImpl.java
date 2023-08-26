package com.nexters.keyme.domain.test.application;

import com.nexters.keyme.domain.member.domain.helper.validator.MemberValidator;
import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.question.domain.internaldto.QuestionStatisticInfo;
import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import com.nexters.keyme.domain.question.domain.repository.QuestionSolvedRepository;
import com.nexters.keyme.domain.question.presentation.dto.response.QuestionSolvedResponse;
import com.nexters.keyme.domain.question.presentation.dto.response.QuestionStatisticResponse;
import com.nexters.keyme.domain.test.domain.internaldto.TestDetailInfo;
import com.nexters.keyme.domain.test.exceptions.NotFoundTestException;
import com.nexters.keyme.domain.test.domain.events.SendNotificationEvent;
import com.nexters.keyme.domain.test.domain.service.provider.TestResultDataProvider;
import com.nexters.keyme.domain.test.domain.service.validator.TestResultValidator;
import com.nexters.keyme.domain.test.domain.service.validator.TestValidator;
import com.nexters.keyme.domain.test.presentation.dto.request.TestListRequest;
import com.nexters.keyme.domain.test.presentation.dto.request.TestSubmissionRequest;
import com.nexters.keyme.domain.test.presentation.dto.response.*;
import com.nexters.keyme.domain.test.domain.service.provider.TestDataProvider;
import com.nexters.keyme.domain.test.domain.service.provider.TestResultCodeProvider;
import com.nexters.keyme.domain.test.domain.internaldto.TestResultStatisticInfo;
import com.nexters.keyme.domain.test.domain.model.Test;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import com.nexters.keyme.domain.test.domain.repository.TestRepository;
import com.nexters.keyme.domain.test.domain.repository.TestResultRepository;
import com.nexters.keyme.domain.test.domain.events.AddStatisticEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {

    private final MemberValidator memberValidator;

    private final TestValidator testValidator;
    private final TestRepository testRepository;
    private final TestDataProvider testDataProvider;

    private final TestResultValidator testResultValidator;
    private final TestResultRepository testResultRepository;
    private final TestResultDataProvider testResultDataProvider;
    private final TestResultCodeProvider testResultCodeProvider;

    private final QuestionSolvedRepository questionSolvedRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    @Override
    public TestDetailResponse getOnboardingTest(Long memberId) {
        MemberEntity member = memberValidator.validateMember(memberId);

        // 없으면 생성
        Test onboardingTest = testDataProvider.getExistOnboardingTest(member).orElse(null);
        if (onboardingTest == null) onboardingTest = testDataProvider.createOnboardingTest(member);

        // Test의 디테일한 정보 + 내 결과
        TestDetailInfo testDetail = testDataProvider.getTestDetail(onboardingTest);
        Long testResultId = testResultRepository.findByTestAndSolver(onboardingTest, member)
                .map(t -> t.getTestResultId())
                .orElse(null);

        return new TestDetailResponse(testDetail, testResultId);
    }

    @Transactional
    @Override
    public TestDetailResponse getDailyTest(Long memberId) {
        MemberEntity member = memberValidator.validateMember(memberId);

        // 없으면 생성
        Test dailyTest = testDataProvider.getExistDailyTest(member).orElse(null);
        if (dailyTest == null) dailyTest = testDataProvider.createDailyTest(member);

        // Test의 디테일한 정보 + 내 결과
        TestDetailInfo testDetail = testDataProvider.getTestDetail(dailyTest);
        Long testResultId = testResultRepository.findByTestAndSolver(dailyTest, member)
                .map(t -> t.getTestResultId())
                .orElse(null);

        return new TestDetailResponse(testDetail, testResultId);
    }

    @Transactional
    @Override
    public TestDetailResponse getSpecificTest(Long memberId, Long testId) {
        MemberEntity member = memberId == null ? null : memberValidator.validateMember(memberId);
        Test test = testValidator.validateTest(testId);

        // Test의 디테일한 정보 + 내 결과
        TestDetailInfo testDetail = testDataProvider.getTestDetail(test);
        Long testResultId = memberId == null ? null :
                testResultRepository.findByTestAndSolver(test, member)
                    .map(t -> t.getTestResultId())
                    .orElse(null);

        return new TestDetailResponse(testDetail, testResultId);
    }

    @Transactional(readOnly = true)
    @Override
    public SingleTestStatisticsResponse getTestStatistics(Long memberId, Long testId) {
        testRepository.findById(testId)
                .map(test -> test.getMember().getId())
                .filter(testOwnerId -> testOwnerId.equals(memberId))
                .orElseThrow(NotFoundTestException::new);

        TestResultStatisticInfo testResultStatisticInfo = testResultRepository.findStatisticsByTestId(testId);
        List<QuestionStatisticInfo> questionStatisticInfoList = questionSolvedRepository.findAllAssociatedQuestionStatisticsByTestId(testId);

        return new SingleTestStatisticsResponse(
            testResultStatisticInfo.getAverageRate(),
            testResultStatisticInfo.getSolvedCount(),
            questionStatisticInfoList.stream()
                .map(QuestionStatisticResponse::new)
                .collect(Collectors.toList())
        );
    }

    // Not MMVP
    @Override
    public List<TestFeedResponse> getTestList(Long memberId, TestListRequest requestInfo) {
        return null;
    }

    @Transactional
    @Override
    public TestSubmitResponse createTestResult(Long solverId, Long testId, TestSubmissionRequest submitInfo) {
        Test test = testValidator.validateTest(testId);
        testResultValidator.validateTestResultAlreadyExist(solverId, test);

        TestResult testResult = testResultDataProvider.createTestResult(solverId, test, submitInfo.getResults());
        String resultCode = solverId != null ? null : testResultCodeProvider.createResultCode(testResult.getTestResultId());

        // FIXME : 너무 강하게 묶여있음
        MemberEntity testOwner = test.getMember();
        eventPublisher.publishEvent(new AddStatisticEvent(testOwner.getId(), solverId, testResult.getQuestionSolvedList()));
        eventPublisher.publishEvent(new SendNotificationEvent(testOwner.getId(), solverId));

        return TestSubmitResponse.builder()
                .testResultId(testResult.getTestResultId())
                .resultCode(resultCode)
                .matchRate(testResult.getMatchRate())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public TestResultResponse getTestResult(Long resultId) {
        TestResult testResult = testResultValidator.validateTestResult(resultId);

        List<QuestionSolved> questionSolvedList = questionSolvedRepository.findAllByTestResultIdWithQuestion(resultId);

        return TestResultResponse.builder()
                .testId(testResult.getTest().getTestId())
                .testResultId(resultId)
                .matchRate(testResult.getMatchRate())
                .results(questionSolvedList.stream()
                        .map(QuestionSolvedResponse::new)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
