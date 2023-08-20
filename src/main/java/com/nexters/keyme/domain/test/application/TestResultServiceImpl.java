package com.nexters.keyme.domain.test.application;

import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.question.domain.model.QuestionSolved;
import com.nexters.keyme.domain.question.domain.repository.QuestionSolvedRepository;
import com.nexters.keyme.domain.member.domain.repository.MemberRepository;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import com.nexters.keyme.domain.test.domain.model.TestResultCode;
import com.nexters.keyme.domain.test.domain.repository.TestResultCodeRepository;
import com.nexters.keyme.domain.test.domain.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestResultServiceImpl implements TestResultService {

    private final TestResultRepository testResultRepository;
    private final QuestionSolvedRepository questionSolvedRepository;
    private final TestResultCodeRepository testResultCodeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void injectTestResultToUser(Long memberId, String resultCode) {
        // 체킹 - member 확인, resultCode확인, TestResult 존재확인
        MemberEntity member = memberRepository.findById(memberId).orElseThrow(ResourceNotFoundException::new);
        TestResultCode testResultCode = testResultCodeRepository.findById(resultCode).orElseThrow(ResourceNotFoundException::new);
        TestResult testResult = testResultRepository.findById(testResultCode.getResultId())
                .filter(ts -> ts.getSolver() == null)
                .orElseThrow(ResourceAlreadyExistsException::new);

        // 유저가 해당 test를 이미 풀었을 경우
        Optional<TestResult> test = testResultRepository.findByTestAndSolver(testResult.getTest(), member);
        testResultCodeRepository.deleteById(resultCode);

        // FIXME - Update가 아닌 예외 던지기로 바꾸기
        if (test.isPresent()) {
            List<QuestionSolved> questionSolvedList = questionSolvedRepository.findAllByTestResultIdWithQuestion(test.get().getTestResultId());
            questionSolvedRepository.deleteAll(questionSolvedList);
            testResultRepository.delete(test.get());
        }

        testResult.setSolver(member);
    }
}