package com.nexters.keyme.domain.clienttest.application;

import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientTestServiceImpl implements ClientTestService {

    @Override
    public void deleteIssuedOnboardingTest(Long memberId) {
        // 삭제
    }

    @Override
    public void deleteIssuedDailyTest(Long memberId) {
        // 삭제
    }
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void clearMember(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(ResourceNotFoundException::new);

        memberRepository.delete(member);
    }
}
