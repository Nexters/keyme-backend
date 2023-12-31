package com.nexters.keyme.domain.test.application;

import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.member.domain.service.validator.MemberValidator;
import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import com.nexters.keyme.domain.question.domain.repository.QuestionSolvedRepository;
import com.nexters.keyme.domain.question.dto.internal.QuestionStatisticInfo;
import com.nexters.keyme.domain.question.dto.response.QuestionSolvedResponse;
import com.nexters.keyme.domain.question.dto.response.QuestionStatisticResponse;
import com.nexters.keyme.domain.test.domain.model.Test;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import com.nexters.keyme.domain.test.domain.repository.TestRepository;
import com.nexters.keyme.domain.test.domain.repository.TestResultRepository;
import com.nexters.keyme.domain.test.domain.service.processor.TestDataProcessor;
import com.nexters.keyme.domain.test.domain.service.processor.TestResultCodeProcessor;
import com.nexters.keyme.domain.test.domain.service.processor.TestResultDataProcessor;
import com.nexters.keyme.domain.test.domain.service.validator.TestResultValidator;
import com.nexters.keyme.domain.test.domain.service.validator.TestValidator;
import com.nexters.keyme.domain.test.dto.internal.TestDetailInfo;
import com.nexters.keyme.domain.test.dto.internal.TestResultStatisticInfo;
import com.nexters.keyme.domain.test.dto.mapper.TestEventMapper;
import com.nexters.keyme.domain.test.dto.request.TestListRequest;
import com.nexters.keyme.domain.test.dto.request.TestSubmissionRequest;
import com.nexters.keyme.domain.test.dto.response.*;
import com.nexters.keyme.domain.test.exceptions.NotFoundTestException;
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
    private final TestDataProcessor testDataProcessor;

    private final TestResultValidator testResultValidator;
    private final TestResultRepository testResultRepository;
    private final TestResultDataProcessor testResultDataProcessor;
    private final TestResultCodeProcessor testResultCodeProcessor;

    private final QuestionSolvedRepository questionSolvedRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final TestEventMapper eventMapper;

    @Transactional
    @Override
    public TestDetailResponse getOnboardingTest(Long memberId) {
        MemberEntity member = memberValidator.validateMember(memberId);

        // 없으면 생성
        Test onboardingTest = testDataProcessor.getExistOnboardingTest(member).orElse(null);
        if (onboardingTest == null) onboardingTest = testDataProcessor.createOnboardingTest(member);

        // Test의 디테일한 정보 + 내 결과
        TestDetailInfo testDetail = testDataProcessor.getTestDetail(onboardingTest);
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
        Test dailyTest = testDataProcessor.getExistDailyTest(member).orElse(null);
        if (dailyTest == null) dailyTest = testDataProcessor.createDailyTest(member);

        // Test의 디테일한 정보 + 내 결과
        TestDetailInfo testDetail = testDataProcessor.getTestDetail(dailyTest);
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
        TestDetailInfo testDetail = testDataProcessor.getTestDetail(test);
        Long testResultId = memberId == null ? null :
                testResultRepository.findByTestAndSolver(test, member)
                    .map(t -> t.getTestResultId())
                    .orElse(null);

        return new TestDetailResponse(testDetail, testResultId);
    }

    @Transactional(readOnly = true)
    @Override
    public SingleTestStatisticsResponse getTestStatistics(Long memberId, Long testId) {
        Long testOwnerId = testRepository.findById(testId)
                .map(test -> test.getMember().getId())
                .filter(ownerId -> ownerId.equals(memberId))
                .orElseThrow(NotFoundTestException::new);

        TestResultStatisticInfo testResultStatisticInfo = testResultRepository.findStatisticsByTestId(testId, testOwnerId);
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

        TestResult testResult = testResultDataProcessor.createTestResult(solverId, test, submitInfo.getResults());
        String resultCode = solverId != null ? null : testResultCodeProcessor.createResultCode(testResult.getTestResultId());

        // FIXME : 너무 강하게 묶여있음
        MemberEntity testOwner = test.getMember();
        eventPublisher.publishEvent(eventMapper.toAddStatisticEvent(testOwner, testResult, solverId));
        eventPublisher.publishEvent(eventMapper.toSendQuestionSolvedNotificationEvent(testOwner, solverId));

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
