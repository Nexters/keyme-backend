package com.nexters.keyme.domain.test.application;

import com.nexters.keyme.domain.member.domain.exceptions.NotFoundMemberException;
import com.nexters.keyme.domain.member.domain.helper.validator.MemberValidator;
import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.question.domain.internaldto.QuestionStatisticInfo;
import com.nexters.keyme.domain.question.domain.model.Question;
import com.nexters.keyme.domain.question.domain.model.QuestionBundle;
import com.nexters.keyme.domain.question.domain.model.QuestionBundleId;
import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import com.nexters.keyme.domain.question.domain.repository.QuestionBundleRepository;
import com.nexters.keyme.domain.question.domain.repository.QuestionRepository;
import com.nexters.keyme.domain.question.domain.repository.QuestionSolvedRepository;
import com.nexters.keyme.domain.question.presentation.dto.response.QuestionResponse;
import com.nexters.keyme.domain.question.presentation.dto.response.QuestionSolvedResponse;
import com.nexters.keyme.domain.question.presentation.dto.response.QuestionStatisticResponse;
import com.nexters.keyme.domain.test.domain.internaldto.TestDetailInfo;
import com.nexters.keyme.domain.test.exceptions.AlreadyExistTestResultException;
import com.nexters.keyme.domain.test.exceptions.InvalidDailyTestException;
import com.nexters.keyme.domain.test.exceptions.NotFoundTestException;
import com.nexters.keyme.domain.test.exceptions.NotFoundTestResultException;
import com.nexters.keyme.domain.test.events.SendNotificationEvent;
import com.nexters.keyme.domain.test.helper.validator.TestValidator;
import com.nexters.keyme.domain.test.presentation.dto.request.TestListRequest;
import com.nexters.keyme.domain.test.presentation.dto.request.TestSubmissionRequest;
import com.nexters.keyme.domain.test.presentation.dto.response.*;
import com.nexters.keyme.domain.member.domain.repository.MemberRepository;
import com.nexters.keyme.domain.test.helper.provider.TestDataProvider;
import com.nexters.keyme.domain.test.helper.provider.TestResultCodeProvider;
import com.nexters.keyme.domain.test.domain.internaldto.TestResultStatisticInfo;
import com.nexters.keyme.domain.test.domain.model.Test;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import com.nexters.keyme.domain.test.domain.repository.TestRepository;
import com.nexters.keyme.domain.test.domain.repository.TestResultRepository;
import com.nexters.keyme.domain.test.events.AddStatisticEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {

    private final TestDataProvider testDataProvider;

    private final MemberValidator memberValidator;
    private final MemberRepository memberRepository;

    private final TestValidator testValidator;
    private final TestRepository testRepository;

    private final TestResultRepository testResultRepository;
    private final TestResultCodeProvider testResultCodeProvider;
    private final QuestionRepository questionRepository;
    private final QuestionSolvedRepository questionSolvedRepository;
    private final QuestionBundleRepository questionBundleRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    @Override
    public TestDetailResponse getOnboardingTest(Long memberId) {
        MemberEntity member = memberValidator.validateMember(memberId);

        Test onboardingTest = testDataProvider.getExistOnboardingTest(member).orElse(null);
        if (onboardingTest == null) onboardingTest = testDataProvider.createOnboardingTest(member);

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

        Test dailyTest = testDataProvider.getExistDailyTest(member).orElse(null);
        if (dailyTest == null) dailyTest = testDataProvider.createDailyTest(member);

        TestDetailInfo testDetail = testDataProvider.getTestDetail(dailyTest);
        Long testResultId = testResultRepository.findByTestAndSolver(dailyTest, member)
                .map(t -> t.getTestResultId())
                .orElse(null);

        return new TestDetailResponse(testDetail, testResultId);


        // 최근 test가 존재하며, 해당 test를 풀지 않았거나 오늘 풀었으면 기존 테스트 리턴
        // FIXME : 체킹로직 메서드로 빼기


        // 랜덤발급으로 수정 필요
        // 온보딩 문제 풀었는지 확인 FIXME : 랜덤발급 로직으로 수정 시 필요없음
        // 온보딩 안풀어도 Daily 호출할 수 있도록
        List<QuestionSolved> questionSolvedList = questionSolvedRepository.findFirstLastestQuestionSolved(memberId, memberId);
        if (questionSolvedList.isEmpty()) {
            throw new InvalidDailyTestException();
        }

        Long lastSolvedQuestionId = questionSolvedList.get(0).getQuestion().getQuestionId();
        List<Question> questionList = questionRepository.findAllById(Arrays.asList(lastSolvedQuestionId + 1, lastSolvedQuestionId + 2, lastSolvedQuestionId + 3));
        Test test = new Test(false, member, questionList.get(0).getTitle());
        testRepository.save(test);
        questionBundleRepository.saveAllAndFlush(
                questionList.stream()
                        .map(question -> new QuestionBundle(new QuestionBundleId(test, question)))
                        .collect(Collectors.toList())
        );

        return TestDetailResponse.builder()
                .testId(test.getTestId())
                .testResultId(null)
                .solvedCount(0)
                .title(test.getTitle())
                .owner(new TestSimpleMemberResponse(member))
                .questions(questionList.stream().map(QuestionResponse::new).collect(Collectors.toList()))
                .build();
    }

    @Transactional
    @Override
    public TestDetailResponse getSpecificTest(Long memberId, Long testId) {
        MemberEntity member = memberId == null ? null : memberValidator.validateMember(memberId);

        Test test = testValidator.validateTest(testId);
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
        /*
            TODO : 로직 순서
            1. validate - test, member
            2. getTestQuestion Statistics
        */


        // test 존재확인, owner가 호출자와 맞는지 확인
        testRepository.findById(testId)
                .map(test -> test.getMember().getId())
                .filter(testOwnerId -> Objects.equals(testOwnerId, memberId))
                .orElseThrow(NotFoundTestException::new);

        // FIXME : 두 통계 쿼리 병렬 수행 필요
        TestResultStatisticInfo testResultStatisticInfo = testResultRepository.findStatisticsByTestId(testId);
        List<QuestionStatisticInfo> questionStatisticInfoList = questionSolvedRepository.findAllAssociatedQuestionStatisticsByTestId(testId);

        return SingleTestStatisticsResponse.builder()
                .averageRate((float) testResultStatisticInfo.getAverageRate())
                .solvedCount(testResultStatisticInfo.getSolvedCount().intValue())
                .questionsStatistics(questionStatisticInfoList.stream()
                        .map(QuestionStatisticResponse::new)
                        .collect(Collectors.toList())
                )
                .build();
    }

    // Not MMVP
    @Override
    public List<TestFeedResponse> getTestList(Long memberId, TestListRequest requestInfo) {
        return null;
    }

    @Transactional
    @Override
    public TestSubmitResponse createTestResult(Long solverId, Long testId, TestSubmissionRequest submitInfo) {
        /*
            TODO : 로직 순서
            1. validate - test, member, testResult 존재유무확인(기명유저인 경우)
            2. 일치율 계산 - 내 test result와 owner의 test result 필요
            3. create test result
            4. event 뿌리기
        */

        Test test = testRepository.findById(testId).orElseThrow(NotFoundTestException::new);
        MemberEntity member = null;

        // FIXME : TestResult 이미 존재하는지 확인하는 로직 domain service로 분리
        // 익명유저가 아닌경우에만
        if (solverId != null) {
            member = memberRepository.findById(solverId).orElseThrow(NotFoundMemberException::new);
            Optional<TestResult> existTestResultOpt = testResultRepository.findByTestAndSolver(test, member);
            if (existTestResultOpt.isPresent()) {
                throw new AlreadyExistTestResultException();
            }
        }

        // TODO : 일치율 계산 로직 추가 및 domain service로 분리
        Float matchRate = Objects.equals(test.getMember().getId(), solverId) ? 100f : 0f;

        // FIXME : Create TestResult 로직 분리
        TestResult testResult = TestResult.builder()
                .matchRate(matchRate)
                .test(test)
                .solver(member)
                .build();
        testResultRepository.save(testResult);

        List<QuestionSolved> questionSolvedList = submitInfo.getResults().stream()
                .map(questionResult -> QuestionSolved.builder()
                        .question(new Question(questionResult.getQuestionId()))
                        .score(questionResult.getScore())
                        .owner(test.getMember())
                        .testResult(testResult)
                        .build()
                )
                .collect(Collectors.toList());
        questionSolvedRepository.saveAll(questionSolvedList);

        // FIXME : 너무 강하게 묶여있음
        eventPublisher.publishEvent(new AddStatisticEvent(test.getMember().getId(), solverId, questionSolvedList));
        eventPublisher.publishEvent(new SendNotificationEvent(test.getMember().getId(), solverId));

        String resultCode = null;
        if (solverId == null) {
            resultCode = testResultCodeProvider.createResultCode(testResult.getTestResultId());
        }

        return TestSubmitResponse.builder()
                .testResultId(testResult.getTestResultId())
                .resultCode(resultCode)
                .matchRate(matchRate)
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public TestResultResponse getTestResult(Long resultId) {
        TestResult testResult = testResultRepository.findById(resultId)
                .orElseThrow(NotFoundTestResultException::new);

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
