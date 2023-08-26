package com.nexters.keyme.domain.test.domain.service.provider;

import com.nexters.keyme.domain.member.domain.helper.validator.MemberValidator;
import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.question.exceptions.NotFoundQuestionException;
import com.nexters.keyme.domain.question.domain.service.QuestionAsyncDataProvider;
import com.nexters.keyme.domain.question.domain.internaldto.QuestionInfo;
import com.nexters.keyme.domain.question.domain.model.Question;
import com.nexters.keyme.domain.question.domain.model.QuestionBundle;
import com.nexters.keyme.domain.question.domain.model.QuestionBundleId;
import com.nexters.keyme.domain.question.domain.repository.QuestionBundleRepository;
import com.nexters.keyme.domain.question.domain.repository.QuestionRepository;
import com.nexters.keyme.domain.test.domain.internaldto.TestDetailInfo;
import com.nexters.keyme.domain.test.domain.internaldto.TestOwnerInfo;
import com.nexters.keyme.domain.test.domain.repository.TestResultRepository;
import com.nexters.keyme.domain.test.domain.model.Test;
import com.nexters.keyme.domain.test.domain.repository.TestRepository;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TestDataProvider {
    final private TestResultAsyncDataProvider testResultAsyncDataProvider;
    final private QuestionAsyncDataProvider questionAsyncDataProvider;
    final private TestRepository testRepository;
    final private TestResultRepository testResultRepository;
    final private QuestionRepository questionRepository;
    final private QuestionBundleRepository questionBundleRepository;
    final private MemberValidator memberValidator;

    @Transactional
    public TestDetailInfo getTestDetail(Test test) {
        List<QuestionBundle> questionBundleList = test.getQuestionBundleList();

        List<Question> questionList = test.getQuestionBundleList()
                .stream()
                .map(qb -> qb.getQuestionBundleId().getQuestion())
                .collect(Collectors.toList());

        MemberEntity owner = test.getMember();

        return TestDetailInfo.builder()
                .testId(test.getTestId())
                .title(questionList.get(0).getTitle())
                .owner(new TestOwnerInfo(owner))
                .questions(questionList.stream().map(q -> new QuestionInfo(q)).collect(Collectors.toList()))
                .build();
    }

    @Transactional(readOnly = true)
    public Optional<Test> getExistOnboardingTest(MemberEntity member) {
        return testRepository.findFirstByMemberAndIsOnboardingOrderByCreatedAtDesc(member, true);
    }

    @Transactional()
    public Test createOnboardingTest(MemberEntity member) {
        List<Question> questionList = questionRepository.findAllByIsOnboarding(true);
        Test test = new Test(true, member, questionList.get(0).getTitle());
        testRepository.save(test);

        List<QuestionBundle> questionBundleList = questionList.stream()
                .map(question -> new QuestionBundle(new QuestionBundleId(test, question)))
                .collect(Collectors.toList());
        questionBundleRepository.saveAll(questionBundleList);
        test.addAllQuestionBundle(questionBundleList);

        return test;
    }

    @Transactional(readOnly = true)
    public Optional<Test> getExistDailyTest(MemberEntity member) {
        Test latestTest = testRepository.findFirstByMemberAndIsOnboardingOrderByCreatedAtDesc(member, false).orElse(null);
        if (latestTest == null) return Optional.empty();

        TestResult testResult = testResultRepository.findByTestAndSolver(latestTest, member).orElse(null);
        if (testResult == null) return Optional.of(latestTest);

        LocalDate today = LocalDate.now();
        if (testResult.getCreatedAt().toLocalDate().isEqual(today)) return Optional.of(latestTest);

        return Optional.empty();
    }

    @Transactional
    public Test createDailyTest(MemberEntity member) {
        Set<Long> allPresentedQuestionIdList = member.getTestList().stream()
                .map(t -> t.getQuestionBundleList())
                .flatMap(qbList -> qbList.stream())
                .map(qb -> qb.getQuestionBundleId().getQuestion().getQuestionId())
                .collect(Collectors.toSet());

        List<Long> availableQuestionIdList = questionRepository.findAll().stream()
                .filter(q -> !q.getIsOnboarding())
                .map(q -> q.getQuestionId())
                .filter(qId -> !allPresentedQuestionIdList.contains(qId))
                .collect(Collectors.toList());

        if (availableQuestionIdList.size() < 3) throw new NotFoundQuestionException();

        SecureRandom random = new SecureRandom();
        Set<Long> useQuestionIdList = new HashSet<>();

        while (useQuestionIdList.size() != 3) {
            int randomIndex = random.nextInt(availableQuestionIdList.size());
            useQuestionIdList.add(availableQuestionIdList.get(randomIndex));
        }

        List<Question> questionList = questionRepository.findAllById(useQuestionIdList);
        Test test = new Test(false, member, questionList.get(0).getTitle());
        testRepository.save(test);

        List<QuestionBundle> questionBundleList = questionList.stream()
                .map(question -> new QuestionBundle(new QuestionBundleId(test, question)))
                .collect(Collectors.toList());
        questionBundleRepository.saveAll(questionBundleList);
        test.addAllQuestionBundle(questionBundleList);

        return test;
    }
}