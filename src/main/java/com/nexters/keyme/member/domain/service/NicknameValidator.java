package com.nexters.keyme.member.domain.service;

import com.nexters.keyme.member.domain.internaldto.ValidationInfo;
import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NicknameValidator {
    private final MemberRepository memberRepository;
    private static final int MAX_NICKNAME_SIZE = 6;

    public ValidationInfo validateNickname(String nickname) {
        if (!isEqualOrLessThanSevenChars(nickname)) {
            return new ValidationInfo(false, "닉네임은 6글자 이하여야 합니다.");
        }
        if (!isUnique(nickname)) {
            return new ValidationInfo(false, "사용 중인 닉네임입니다.");
        }

        return new ValidationInfo(true, "사용 가능한 닉네임입니다");
    }

    private boolean isEqualOrLessThanSevenChars(String nickname) {
        return nickname.length() <= MAX_NICKNAME_SIZE;
    }

    private boolean isUnique(String nickname) {
        Optional<MemberEntity> member = memberRepository.findByNickname(nickname);
        return member.isEmpty();
    }
}
