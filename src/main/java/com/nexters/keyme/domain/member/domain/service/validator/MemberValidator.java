package com.nexters.keyme.domain.member.domain.service.validator;

import com.nexters.keyme.domain.member.exceptions.NotFoundMemberException;
import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberValidator {

    private final MemberRepository memberRepository;

    public MemberEntity validateMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);
    }
}
