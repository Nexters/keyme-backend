package com.nexters.keyme.member.application;

import com.nexters.keyme.domain.member.application.MemberService;
import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import com.nexters.keyme.domain.member.domain.repository.MemberRepository;
import com.nexters.keyme.domain.member.domain.service.validator.MemberValidator;
import com.nexters.keyme.domain.member.dto.request.MemberModificationRequest;
import com.nexters.keyme.domain.member.dto.request.NicknameVerificationRequest;
import com.nexters.keyme.domain.member.dto.response.MemberResponse;
import com.nexters.keyme.domain.member.dto.response.NicknameVerificationResponse;
import com.nexters.keyme.domain.member.exceptions.DeletedMemberException;
import com.nexters.keyme.domain.member.exceptions.NicknameDuplicateException;
import com.nexters.keyme.global.common.dto.internal.UserInfo;
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

    @Autowired
    private MemberValidator memberValidator;

    @Autowired
    private MemberRepository memberRepository;

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
        assertThatThrownBy(() -> memberService.verifyNickname(finalRequest)).isInstanceOf(NicknameDuplicateException.class);

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

    @Test
    @DisplayName("멤버 삭제 통합테스트")
    void deleteMemberTest() {
        MemberEntity member = memberValidator.validateMember(3L);
        assertThat(member.isDeleted()).isFalse();

        memberService.deleteMember(3L);

        assertThat(member.isDeleted()).isTrue();
        assertThatThrownBy(() -> memberValidator.validateMember(3L)).isInstanceOf(DeletedMemberException.class);
    }
}
