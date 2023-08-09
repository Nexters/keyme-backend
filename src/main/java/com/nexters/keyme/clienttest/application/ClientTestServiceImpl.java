package com.nexters.keyme.clienttest.application;


import com.nexters.keyme.common.exceptions.ResourceNotFoundException;
import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientTestServiceImpl implements ClientTestService {
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void clearMember(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(ResourceNotFoundException::new);

        memberRepository.delete(member);
    }
}
