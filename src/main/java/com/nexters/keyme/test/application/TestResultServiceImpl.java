package com.nexters.keyme.test.application;

import com.nexters.keyme.common.exceptions.ResourceAlreadyExistsException;
import com.nexters.keyme.common.exceptions.ResourceNotFoundException;
import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.member.domain.repository.MemberRepository;
import com.nexters.keyme.test.domain.model.TestResult;
import com.nexters.keyme.test.domain.model.TestResultCode;
import com.nexters.keyme.test.domain.repository.TestResultCodeRepository;
import com.nexters.keyme.test.domain.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestResultServiceImpl implements TestResultService {

    private final TestResultRepository testResultRepository;
    private final TestResultCodeRepository testResultCodeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void injectTestResultToUser(Long memberId, String resultCode) {
        // 체킹 - member 확인, resultCode확인, TestResult 확인
        MemberEntity member = memberRepository.findById(memberId).orElseThrow(ResourceNotFoundException::new);
        TestResultCode testResultCode = testResultCodeRepository.findById(resultCode).orElseThrow(ResourceNotFoundException::new);
        TestResult testResult = testResultRepository.findById(testResultCode.getResultId())
                .filter(ts -> ts.getSolver() == null)
                .orElseThrow(ResourceAlreadyExistsException::new);

        testResultCodeRepository.deleteById(resultCode);
        testResult.setSolver(member);
    }
}