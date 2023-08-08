package com.nexters.keyme.member.domain.model;

import com.nexters.keyme.member.domain.internaldto.MemberModificationInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberEntityTest {

    @Test
    @DisplayName("멤버 정보 수정 테스트")
    void modifyMemberInfo() {
        MemberEntity member = MemberEntity.builder()
                .id(1L)
                .nickname("nicknameOne")
                .profileImage(new ProfileImage("originalOne", "thumbnailOne"))
                .build();

        MemberModificationInfo request = MemberModificationInfo.builder()
                .nickname("nicknameTwo")
                .originalImage("originalTwo")
                .thumbnailImage("thumbnailTwo")
                .build();

        member.modifyMemberInfo(request);

        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getNickname()).isEqualTo("nicknameTwo");
        assertThat(member.getProfileImage().getOriginalUrl()).isEqualTo("originalTwo");
        assertThat(member.getProfileImage().getThumbnailUrl()).isEqualTo("thumbnailTwo");
    }
}