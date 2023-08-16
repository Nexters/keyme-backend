package com.nexters.keyme.test.domain.helper;

import com.nexters.keyme.common.exceptions.ResourceNotFoundException;
import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.member.domain.repository.MemberRepository;
import com.nexters.keyme.question.domain.helper.QuestionAsyncDataProvider;
import com.nexters.keyme.question.domain.model.Question;
import com.nexters.keyme.question.presentation.dto.response.QuestionResponse;
import com.nexters.keyme.test.domain.model.Test;
import com.nexters.keyme.test.domain.model.TestResult;
import com.nexters.keyme.test.domain.repository.TestRepository;
import com.nexters.keyme.test.presentation.dto.response.TestDetailResponse;
import com.nexters.keyme.test.presentation.dto.response.TestSimpleMemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestDataProvider {
    final private TestResultAsyncDataProvider testResultAsyncDataProvider;
    final private QuestionAsyncDataProvider questionAsyncDataProvider;
    final private TestRepository testRepository;
    final private MemberRepository memberRepository;

    @Transactional
    public TestDetailResponse getTestDeatil(Long testId, Long solverId) {
        Test test = testRepository.findById(testId).orElseThrow(ResourceNotFoundException::new);
        MemberEntity member = solverId == null ? null : memberRepository.findById(solverId).orElseThrow(ResourceNotFoundException::new);

        CompletableFuture<Optional<TestResult>> testResultFuture = testResultAsyncDataProvider.asyncFindByTestAndSolver(test, member);
        CompletableFuture<List<Question>> questionListFuture = questionAsyncDataProvider.asyncFindAllQuestionByTestId(testId);

        return questionListFuture.thenCombine(testResultFuture, (questionList, testResultOpt) -> {
            Long testResultId = testResultOpt.map(ts -> ts.getTestResultId()).orElse(null);
            List<QuestionResponse> questionResponseList = questionList.stream()
                    .map(QuestionResponse::new)
                    .collect(Collectors.toList());
            return TestDetailResponse.builder()
                    .testId(testId)
                    .testResultId(testResultId)
                    .title(test.getTitle())
                    .solvedCount(test.getTestResultList().size()) // ??? 어떻게 처리할 것인가
                    .owner(new TestSimpleMemberResponse(test.getMember()))
                    .questions(questionResponseList)
                    .build();
        }).join();
    }
}
