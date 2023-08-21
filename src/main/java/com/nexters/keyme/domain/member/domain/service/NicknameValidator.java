package com.nexters.keyme.domain.member.domain.service;

import com.nexters.keyme.domain.member.domain.exceptions.NicknameDuplicateException;
import com.nexters.keyme.domain.member.domain.exceptions.NicknameTooLongException;
import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.member.domain.internaldto.ValidationInfo;
import com.nexters.keyme.domain.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NicknameValidator {
    private final MemberRepository memberRepository;
    private static final int MAX_NICKNAME_SIZE = 6;

    public ValidationInfo validateNickname(String nickname) {
        if (!checkLength(nickname)) {
            throw new NicknameTooLongException();
        }
        if (!isUnique(nickname)) {
            throw new NicknameDuplicateException();
        }

        return new ValidationInfo(true, "사용 가능한 닉네임입니다");
    }

    private boolean checkLength(String nickname) {
        return nickname.length() <= MAX_NICKNAME_SIZE;
    }

    private boolean isUnique(String nickname) {
        Optional<MemberEntity> member = memberRepository.findByNickname(nickname);
        return member.isEmpty();
    }
}
