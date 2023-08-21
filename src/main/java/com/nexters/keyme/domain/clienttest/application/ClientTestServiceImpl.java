package com.nexters.keyme.domain.clienttest.application;

import com.nexters.keyme.domain.member.domain.exceptions.NotFoundMemberException;
import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.member.domain.repository.MemberRepository;
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
                .orElseThrow(NotFoundMemberException::new);

        memberRepository.delete(member);
    }
}
