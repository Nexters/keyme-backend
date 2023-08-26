package com.nexters.keyme.domain.test.domain.service.processor;


import com.nexters.keyme.domain.member.domain.service.validator.MemberValidator;
import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.question.domain.model.Question;
import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import com.nexters.keyme.domain.question.domain.repository.QuestionSolvedRepository;
import com.nexters.keyme.domain.test.domain.model.Test;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import com.nexters.keyme.domain.test.domain.repository.TestResultRepository;
import com.nexters.keyme.domain.test.exceptions.InvalidTestResultSubmitException;
import com.nexters.keyme.domain.test.exceptions.NotFoundTestResultException;
import com.nexters.keyme.domain.test.dto.request.TestSubmissionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TestResultDataProcessor {

    private final MemberValidator memberValidator;
    private final TestResultRepository testResultRepository;
    private final QuestionSolvedRepository questionSolvedRepository;

    @Transactional
    public TestResult createTestResult(Long solverId, Test test, List<TestSubmissionRequest.QuestionSubmission> answers) {
        MemberEntity solver = solverId == null ? null : memberValidator.validateMember(solverId);
        MemberEntity owner = test.getMember();

        // answers 체크
        Set<Long> answerQuestionIdSet = answers.stream().map(answer -> answer.getQuestionId())
                .collect(Collectors.toSet());
        Set<Long> questionIdSet = test.getQuestionBundleList().stream()
                .map(qb -> qb.getQuestionBundleId().getQuestion().getQuestionId())
                .collect(Collectors.toSet());

        if (answerQuestionIdSet.size() != questionIdSet.size() || !answerQuestionIdSet.containsAll(questionIdSet))
            throw new InvalidTestResultSubmitException();

        // owner result와 비교해 matchRate 생성
        Double matchRate = 100.0;
        if (solver == null || !solver.getId().equals(owner.getId())) {
            TestResult ownerResult = testResultRepository.findByTestAndSolver(test, owner)
                    .orElseThrow(NotFoundTestResultException::new);
            List<QuestionSolved> sortedOwnerQuestionSolved = questionSolvedRepository.findAllByTestResultOrderByQuestion(ownerResult);
            List<TestSubmissionRequest.QuestionSubmission> sortedSubmissionList = answers.stream()
                    .sorted((a, b) -> a.getQuestionId().compareTo(b.getQuestionId()))
                    .collect(Collectors.toList());
            matchRate = calculateTestResultMatchRate(sortedOwnerQuestionSolved, sortedSubmissionList);
        }

        // TestResult 생성
        TestResult testResult = new TestResult(test, solver, matchRate);
        testResultRepository.save(testResult);

        List<QuestionSolved> questionSolvedList = answers.stream()
                .map(answer -> QuestionSolved.builder()
                        .question(new Question(answer.getQuestionId()))
                        .score(answer.getScore())
                        .owner(owner)
                        .testResult(testResult)
                        .build()
                )
                .collect(Collectors.toList());
        questionSolvedRepository.saveAll(questionSolvedList);
        testResult.addAllQuestionSolved(questionSolvedList);

        return testResult;
    }

    private Double calculateTestResultMatchRate(List<QuestionSolved> sortedOwnerQuestionSolved, List<TestSubmissionRequest.QuestionSubmission> sortedSubmissionList) {
        int diffSum = 0;
        int questionCount = sortedOwnerQuestionSolved.size();
        for (int i = 0; i < questionCount; i++) {
            int ownerScore = sortedOwnerQuestionSolved.get(i).getScore();
            int solverScore = sortedSubmissionList.get(i).getScore();

            diffSum += 100 - (Math.abs(ownerScore - solverScore) * 2 * 10);
        }

        return diffSum / ((double) questionCount);
    }
}
