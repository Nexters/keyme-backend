package com.nexters.keyme.member.domain.service;

import com.nexters.keyme.member.domain.exceptions.NicknameVerificationException;
import com.nexters.keyme.member.domain.internaldto.ValidationInfo;
import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.member.domain.repository.MemberRepository;
import com.nexters.keyme.member.domain.state.MemberStateCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NicknameValidator {
    private final MemberRepository memberRepository;
    private static final int MAX_NICKNAME_SIZE = 6;

    public ValidationInfo validateNickname(String nickname) {
        if (!isEqualOrLessThanSixChars(nickname)) {
            throw new NicknameVerificationException(MemberStateCode.NICKNAME_TOO_LONG);
        }
        if (!isUnique(nickname)) {
            throw new NicknameVerificationException(MemberStateCode.DUPLICATED_NICKNAME);
        }

        return new ValidationInfo(true, "사용 가능한 닉네임입니다");
    }

    private boolean isEqualOrLessThanSixChars(String nickname) {
        return nickname.length() <= MAX_NICKNAME_SIZE;
    }

    private boolean isUnique(String nickname) {
        Optional<MemberEntity> member = memberRepository.findByNickname(nickname);
        return member.isEmpty();
    }
}
