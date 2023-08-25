package com.nexters.keyme.domain.test.helper.validator;

import com.nexters.keyme.domain.member.domain.helper.validator.MemberValidator;
import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.test.domain.model.Test;
import com.nexters.keyme.domain.test.exceptions.AlreadyExistTestResultException;
import com.nexters.keyme.domain.test.exceptions.NotFoundTestResultException;
import com.nexters.keyme.domain.test.domain.model.TestResult;
import com.nexters.keyme.domain.test.domain.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TestResultValidator {

    private final MemberValidator memberValidator;
    private final TestResultRepository testResultRepository;

    @Transactional(readOnly = true)
    public TestResult validateTestResult(Long testResultId) {
        return testResultRepository.findById(testResultId)
                .orElseThrow(NotFoundTestResultException::new);
    }

    @Transactional(readOnly = true)
    public void validateTestResultAlreadyExist(Long solverId, Test test) {
        // 익명유저
        if (solverId == null) return;

        MemberEntity member = memberValidator.validateMember(solverId);
        TestResult testResult = testResultRepository.findByTestAndSolver(test, member).orElse(null);

        if (testResult != null) throw new AlreadyExistTestResultException();
    }
}
