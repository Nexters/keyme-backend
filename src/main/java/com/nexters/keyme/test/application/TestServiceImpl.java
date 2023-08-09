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

    final private TestDataProvider testDataProvider;
    final private TestRepository testRepository;
    final private MemberRepository memberRepository;
    final private TestResultRepository testResultRepository;
    final private QuestionRepository questionRepository;
    final private QuestionSolvedRepository questionSolvedRepository;
    final private QuestionBundleRepository questionBundleRepository;

    @Transactional
    @Override
    public TestDetailResponse getOrCreateOnboardingTest(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId).orElseThrow(ResourceNotFoundException::new);

        Optional<Test> testList = testRepository.findFirstByMemberAndIsOnboardingOrderByCreatedAtDesc(member, true);
        if (testList.isPresent()) {
            log.info("기존에 존재하는 온보딩 테스트 리턴");
            Long testId = testList.get().getTestId();
            return testDataProvider.getTestDeatil(testId, memberId);
        }

        List<Question> questionList = questionRepository.findAllByIsOnboarding(true);
        Test test = new Test(true, member, questionList.get(0).getDescription());
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
    public TestDetailResponse getOrCreateDailyTest(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId).orElseThrow(ResourceNotFoundException::new);
        Optional<Test> testOpt = testRepository.findFirstByMemberAndIsOnboardingOrderByCreatedAtDesc(member, false);
        Optional<TestResult> testResultOpt = null;

        if (testOpt.isPresent()) {
            testResultOpt = testResultRepository.findByTestAndSolver(testOpt.get(), member);
        }

        // 있으면 기존꺼 응답
        LocalDate today = LocalDate.now();
        if (testOpt.isPresent() && (
                testResultOpt.isEmpty() ||
                !testResultOpt.get().getCreatedAt().toLocalDate().isEqual(today) )
        ) {
            log.info("기존에 존재하는 데일리 테스트 리턴");
            return testDataProvider.getTestDeatil(testOpt.get().getTestId(), memberId);
        }

        // 랜덤발급으로 수정 필요
        // 온보딩 문제 풀었는지 확인
        List<QuestionSolved> questionSolvedList = questionSolvedRepository.findFirstLastestQuestionSolved(memberId, memberId);
        if (questionSolvedList.isEmpty()) {
            log.error("온보딩 문제를 풀기 전 데일리 문제 호출");
            throw new InvalidRequestException();
        }
        log.error("question solved 받아옴");

        Long lastSolvedQuestionId = questionSolvedList.get(0).getQuestion().getQuestionId();
        List<Question> questionList = questionRepository.findAllById(Arrays.asList(lastSolvedQuestionId + 1, lastSolvedQuestionId + 2, lastSolvedQuestionId + 3));
        Test test = new Test(false, member, questionList.get(0).getDescription());
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
        return testDataProvider.getTestDeatil(testId, solvedMemberId);
    }

    @Override
    public SingleTestStatisticsResponse getTestStatistics(Long memberId, Long testId) {
        Test test = testRepository.findById(testId).orElseThrow(ResourceNotFoundException::new);

        if (test.getMember().getId() != memberId) {
            log.error("권한 없는 test의 통계에 접근 : " + memberId);
            throw new AccessDeniedException();
        }

        Optional<TestResultStatisticInfo> testResultStatisticInfo = testResultRepository.findStatisticsByTestId(testId);
        List<QuestionStatisticInfo> questionStatisticInfoList = questionSolvedRepository.findAllAssociatedQuestionStatisticsByTestId(testId);

        if (testResultStatisticInfo.isEmpty()) {
            log.error("문제를 아무도 풀지 않음(나 포함) - " + "member : " + memberId + "test : " + testId);
            throw new AccessDeniedException();
        }

        return SingleTestStatisticsResponse.builder()
                .averageRate((float) testResultStatisticInfo.get().getAverageRate())
                .solvedCount(testResultStatisticInfo.get().getSolvedCount().intValue())
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
        Optional<MemberEntity> member = memberRepository.findById(solverId);
        Test test = testRepository.findById(testId).orElseThrow(ResourceNotFoundException::new);

        // 익명유저가 아닌경우
        if (member.isPresent()) {
            Optional<TestResult> existTestResultOpt = testResultRepository.findByTestAndSolver(test, member.get());
            if (existTestResultOpt.isPresent()) {
                log.error("Client Error : Test 결과가 이미 존재합니다.");
                throw new ResourceAlreadyExistsException();
            }
        }

        Float matchRate = test.getMember().getId() == solverId ? 100f : 0f;
        TestResult testResult = TestResult.builder()
                .matchRate(matchRate)
                .test(test)
                .solver(member.orElse(null))
                .build();
        List<QuestionSolved> questionSolvedList = submitInfo.getResults().stream()
                .map(questionResult -> QuestionSolved.builder()
                        .question(new Question(questionResult.getQuestionId()))
                        .score(questionResult.getScore())
                        .testResult(testResult)
                        .build()
                )
                .collect(Collectors.toList());

        questionSolvedRepository.saveAll(questionSolvedList);
        testResultRepository.save(testResult);

        return TestSubmitResponse.builder()
                .testResultId(testResult.getTestResultId())
                .matchRate(matchRate)
                .build();
    }

    @Override
    public TestResultResponse getTestResult(Long testId, Long resultId) {
        TestResult testResult = testResultRepository.findById(resultId).orElseThrow(ResourceNotFoundException::new);

        if (testResult.getTest().getTestId() != testId) {
            log.error("잘못된 매칭 : test - test result ");
            throw new ResourceNotFoundException();
        }

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
