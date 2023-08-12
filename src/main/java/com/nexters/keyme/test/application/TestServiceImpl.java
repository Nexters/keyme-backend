package com.nexters.keyme.test.application;

import com.nexters.keyme.common.exceptions.AccessDeniedException;
import com.nexters.keyme.common.exceptions.InvalidRequestException;
import com.nexters.keyme.common.exceptions.ResourceAlreadyExistsException;
import com.nexters.keyme.common.exceptions.ResourceNotFoundException;
import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.member.domain.repository.MemberRepository;
import com.nexters.keyme.question.domain.internaldto.QuestionStatisticInfo;
import com.nexters.keyme.question.domain.model.Question;
import com.nexters.keyme.question.domain.model.QuestionBundle;
import com.nexters.keyme.question.domain.model.QuestionBundleId;
import com.nexters.keyme.question.domain.model.QuestionSolved;
import com.nexters.keyme.question.domain.repository.QuestionBundleRepository;
import com.nexters.keyme.question.domain.repository.QuestionRepository;
import com.nexters.keyme.question.domain.repository.QuestionSolvedRepository;
import com.nexters.keyme.question.presentation.dto.response.QuestionResponse;
import com.nexters.keyme.question.presentation.dto.response.QuestionSolvedResponse;
import com.nexters.keyme.question.presentation.dto.response.QuestionStatisticResponse;
import com.nexters.keyme.test.domain.helper.TestDataProvider;
import com.nexters.keyme.test.domain.helper.TestResultCodeProvider;
import com.nexters.keyme.test.domain.internaldto.TestResultStatisticInfo;
import com.nexters.keyme.test.domain.model.Test;
import com.nexters.keyme.test.domain.model.TestResult;
import com.nexters.keyme.test.domain.repository.TestRepository;
import com.nexters.keyme.test.domain.repository.TestResultRepository;
import com.nexters.keyme.test.presentation.dto.request.TestListRequest;
import com.nexters.keyme.test.presentation.dto.request.TestSubmissionRequest;
import com.nexters.keyme.test.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {

    private final TestDataProvider testDataProvider;
    private final MemberRepository memberRepository;
    private final TestRepository testRepository;
    private final TestResultRepository testResultRepository;
    private final TestResultCodeProvider testResultCodeProvider;
    private final QuestionRepository questionRepository;
    private final QuestionSolvedRepository questionSolvedRepository;
    private final QuestionBundleRepository questionBundleRepository;

    @Transactional
    @Override
    public TestDetailResponse getOrCreateOnboardingTest(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId).orElseThrow(ResourceNotFoundException::new);
        Long existOnboardingId = testRepository.findFirstByMemberAndIsOnboardingOrderByCreatedAtDesc(member, true)
                .map(test -> test.getTestId())
                .orElse(null);

        if (existOnboardingId != null) {
            return testDataProvider.getTestDeatil(existOnboardingId, memberId);
        }

        // FIXME : Create Test - member, questionList 필요
        List<Question> questionList = questionRepository.findAllByIsOnboarding(true);
        Test test = new Test(true, member, questionList.get(0).getTitle());
        testRepository.save(test);
        questionBundleRepository.saveAllAndFlush(
            questionList.stream()
                .map(question -> new QuestionBundle(new QuestionBundleId(test, question)))
                .collect(Collectors.toList())
        );

        // FIXME : Create TestDeatilResponse - test, member, questionList
        return TestDetailResponse.builder()
                .testId(test.getTestId())
                .testResultId(null)
                .solvedCount(0)
                .title(test.getTitle())
                .presenterProfile(new TestSimpleMemberResponse(member))
                .questions(questionList.stream().map(QuestionResponse::new).collect(Collectors.toList()))
                .build();
    }

    @Transactional
    @Override
    public TestDetailResponse getOrCreateDailyTest(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId).orElseThrow(ResourceNotFoundException::new);

        // 최근 test가 존재하며, 해당 test를 풀지 않았거나 오늘 풀었으면 기존 테스트 리턴
        // FIXME : 체킹로직 메서드로 빼기
        Optional<Test> latesetTestOpt = testRepository.findFirstByMemberAndIsOnboardingOrderByCreatedAtDesc(member, false);
        Optional<TestResult> testResultOpt = null;
        if (latesetTestOpt.isPresent()) {
            testResultOpt = testResultRepository.findByTestAndSolver(latesetTestOpt.get(), member);
        }

        LocalDate today = LocalDate.now();
        if (latesetTestOpt.isPresent() && (
                testResultOpt.isEmpty() ||
                !testResultOpt.get().getCreatedAt().toLocalDate().isEqual(today) )
        ) {
            log.info("기존에 존재하는 데일리 테스트 리턴");
            return testDataProvider.getTestDeatil(latesetTestOpt.get().getTestId(), memberId);
        }

        // 랜덤발급으로 수정 필요
        // 온보딩 문제 풀었는지 확인 FIXME : 랜덤발급 로직으로 수정 시 필요없음
        List<QuestionSolved> questionSolvedList = questionSolvedRepository.findFirstLastestQuestionSolved(memberId, memberId);
        if (questionSolvedList.isEmpty()) {
            log.error("온보딩 문제를 풀기 전 데일리 문제 호출");
            throw new InvalidRequestException();
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
                .presenterProfile(new TestSimpleMemberResponse(member))
                .questions(questionList.stream().map(QuestionResponse::new).collect(Collectors.toList()))
                .build();
    }

    @Transactional
    @Override
    public TestDetailResponse getSpecificTest(Long solvedMemberId, Long testId) {
        // FIXME : solver Optional로 래핑 - null일 수 있음을 명시
        return testDataProvider.getTestDeatil(testId, solvedMemberId);
    }

    @Override
    public SingleTestStatisticsResponse getTestStatistics(Long memberId, Long testId) {
        // test 존재확인, owner가 호출자와 맞는지 확인
        testRepository.findById(testId)
                .map(test -> test.getMember().getId())
                .filter(testOwnerId -> testOwnerId == memberId)
                .orElseThrow(ResourceNotFoundException::new);

        // FIXME : 두 통계 쿼리 병렬 수행 필요
        TestResultStatisticInfo testResultStatisticInfo = testResultRepository.findStatisticsByTestId(testId).orElseThrow(ResourceNotFoundException::new);
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
        Test test = testRepository.findById(testId).orElseThrow(ResourceNotFoundException::new);
        MemberEntity member = null;

        // FIXME : TestResult 이미 존재하는지 확인하는 로직 domain service로 분리
        // 익명유저가 아닌경우에만
        if (solverId != null) {
            member = memberRepository.findById(solverId).orElseThrow(ResourceNotFoundException::new);
            Optional<TestResult> existTestResultOpt = testResultRepository.findByTestAndSolver(test, member);
            if (existTestResultOpt.isPresent()) {
                log.error("Client Error : Test 결과가 이미 존재합니다.");
                throw new ResourceAlreadyExistsException();
            }
        }

        // TODO : 일치율 계산 로직 추가 및 domain service로 분리
        Float matchRate = test.getMember().getId() == solverId ? 100f : 0f;

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
                        .testResult(testResult)
                        .build()
                )
                .collect(Collectors.toList());
        questionSolvedRepository.saveAll(questionSolvedList);


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

    @Override
    public TestResultResponse getTestResult(Long testId, Long resultId) {
        TestResult testResult = testResultRepository.findById(resultId)
                .filter(ts -> ts.getTest().getTestId() == testId)
                .orElseThrow(ResourceNotFoundException::new);

        List<QuestionSolved> questionSolvedList = questionSolvedRepository.findAllByTestResultIdWithQuestion(resultId);

        return TestResultResponse.builder()
                .testId(testId)
                .testResultId(resultId)
                .matchRate(testResult.getMatchRate())
                .results(questionSolvedList.stream()
                        .map(QuestionSolvedResponse::new)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
