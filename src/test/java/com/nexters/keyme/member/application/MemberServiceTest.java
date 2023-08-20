package com.nexters.keyme.member.application;

import com.nexters.keyme.domain.auth.domain.internaldto.UserInfo;
import com.nexters.keyme.domain.member.application.MemberService;
import com.nexters.keyme.domain.member.domain.exceptions.NicknameVerificationException;
import com.nexters.keyme.domain.member.presentation.dto.request.MemberModificationRequest;
import com.nexters.keyme.domain.member.presentation.dto.request.NicknameVerificationRequest;
import com.nexters.keyme.domain.member.presentation.dto.response.NicknameVerificationResponse;
import com.nexters.keyme.domain.member.presentation.dto.response.MemberResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        assertThat(response.getProfileThumbnail()).isEqualTo("keyme.space.thumbnail");
    }

    @Test
    @DisplayName("닉네임 검증 통합테스트")
    void verifyNickname() {
        NicknameVerificationRequest request = new NicknameVerificationRequest("nick");
        NicknameVerificationRequest finalRequest = request;
        assertThatThrownBy(() -> memberService.verifyNickname(finalRequest)).isInstanceOf(NicknameVerificationException.class);

        request = new NicknameVerificationRequest("tom");
        NicknameVerificationResponse response = memberService.verifyNickname(request);
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
        assertThat(response.getProfileThumbnail()).isEqualTo("newThumbnail");
    }
}
