package com.nexters.keyme.domain.member.domain.service;

import com.nexters.keyme.domain.member.exceptions.NicknameDuplicateException;
import com.nexters.keyme.domain.member.exceptions.NicknameTooLongException;
import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.member.domain.repository.MemberRepository;
import com.nexters.keyme.domain.member.domain.service.validator.NicknameValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class NicknameValidatorTest {
    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    NicknameValidator validator;

    @Test
    @DisplayName("닉네임 유효성 검사 테스트")
    void validateNickname() {
        Mockito.when(memberRepository.findByNickname("sample")).thenReturn(Optional.of(new MemberEntity()));
        assertThatThrownBy(() -> validator.validateNickname("sample")).isInstanceOf(NicknameDuplicateException.class);

        assertThatThrownBy(() -> validator.validateNickname("sample1234")).isInstanceOf(NicknameTooLongException.class);
        assertThatThrownBy(() -> validator.validateNickname("너무긴한글닉네임")).isInstanceOf(NicknameTooLongException.class);

        assertThat(validator.validateNickname("sam123").isValid()).isTrue();
        assertThat(validator.validateNickname("한글닉네임").isValid()).isTrue();
    }
}