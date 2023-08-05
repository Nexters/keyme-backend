package com.nexters.keyme.member.application;

import com.nexters.keyme.auth.dto.UserInfo;
import com.nexters.keyme.member.presentation.dto.MemberModificationRequest;
import com.nexters.keyme.member.presentation.dto.NicknameVerificationRequest;
import com.nexters.keyme.member.presentation.dto.NicknameVerificationResponse;
import com.nexters.keyme.member.presentation.dto.response.MemberResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    void getOrCreateMember() {

    }

    @Test
    @DisplayName("멤버 정보 가져오기 통합테스트")
    void getMemberInfo() {
        MemberResponse response = memberService.getMemberInfo(1L);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getNickname()).isEqualTo("nick");
        assertThat(response.getFriendCode()).isEqualTo("ABCDEFG");
        assertThat(response.getProfileImage()).isEqualTo("keyme.space/original");
        assertThat(response.getProfileTumbnail()).isEqualTo("keyme.space.thumbnail");
    }

    @Test
    @DisplayName("닉네임 검증 통합테스트")
    void verifyNickname() {
        NicknameVerificationRequest request = new NicknameVerificationRequest("nick");
        NicknameVerificationResponse response = memberService.verifyNickname(request);
        assertThat(response.isValid()).isFalse();

        request = new NicknameVerificationRequest("tom");
        response = memberService.verifyNickname(request);
        assertThat(response.isValid()).isTrue();
    }

    @Test
    void modifyMemberInfo() {
        MemberModificationRequest request = MemberModificationRequest.builder()
                .nickname("chames")
                .profileImage("newOrg")
                .profileThumbnail("newThumbnail")
                .build();

        MemberResponse response = memberService.modifyMemberInfo(request, new UserInfo(1L));

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getNickname()).isEqualTo("chames");
        assertThat(response.getFriendCode()).isEqualTo("ABCDEFG");
        assertThat(response.getProfileImage()).isEqualTo("newOrg");
        assertThat(response.getProfileTumbnail()).isEqualTo("newThumbnail");
    }
}
