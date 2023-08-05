package com.nexters.keyme.member.domain.service;

import com.nexters.keyme.member.domain.model.MemberEntity;
import com.nexters.keyme.member.domain.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(validator.validateNickname("sample").isValid()).isFalse();

        assertThat(validator.validateNickname("sample1234").isValid()).isFalse();
        assertThat(validator.validateNickname("너무긴한글닉네임").isValid()).isFalse();

        assertThat(validator.validateNickname("sample1").isValid()).isFalse();
        assertThat(validator.validateNickname("sam123").isValid()).isTrue();
        assertThat(validator.validateNickname("한글닉네임").isValid()).isTrue();
    }
}