package com.nexters.keyme.member.domain.model;

import com.nexters.keyme.member.domain.internaldto.MemberModificationInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberEntityTest {

    @Test
    @DisplayName("수정 요청 필드에 null이 없을 때에는 닉네임과 이미지를 모두 수정한다")
    void modifyAllFieldsTest() {
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

    @Test
    @DisplayName("수정 요청 필드 중 닉네임이 null일 때에는 섬네일만 수정한다")
    void modifyProfileImageOnlyTest() {
        MemberEntity member = MemberEntity.builder()
                .id(1L)
                .nickname("nicknameOne")
                .profileImage(new ProfileImage("originalOne", "thumbnailOne"))
                .build();

        MemberModificationInfo request = MemberModificationInfo.builder()
                .nickname(null)
                .originalImage("originalTwo")
                .thumbnailImage("thumbnailTwo")
                .build();

        member.modifyMemberInfo(request);

        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getNickname()).isEqualTo("nicknameOne");
        assertThat(member.getProfileImage().getOriginalUrl()).isEqualTo("originalTwo");
        assertThat(member.getProfileImage().getThumbnailUrl()).isEqualTo("thumbnailTwo");
    }

    @Test
    @DisplayName("수정 요청 필드 중 프로필 사진 원본/섬네일 중 일부가 null일 때에는 프로필 사진을 업데이트하지 않는다.")
    void modifyNicknameOnlyTest() {
        MemberEntity member = MemberEntity.builder()
                .id(1L)
                .nickname("nicknameOne")
                .profileImage(new ProfileImage("originalOne", "thumbnailOne"))
                .build();

        MemberModificationInfo requestOne = MemberModificationInfo.builder()
                .nickname("nicknameTwo")
                .originalImage("originalTwo")
                .thumbnailImage(null)
                .build();

        MemberModificationInfo requestTwo = MemberModificationInfo.builder()
                .nickname("nicknameTwo")
                .originalImage(null)
                .thumbnailImage("thumbnailTwo")
                .build();

        member.modifyMemberInfo(requestOne);

        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getNickname()).isEqualTo("nicknameTwo");
        assertThat(member.getProfileImage().getOriginalUrl()).isEqualTo("originalOne");
        assertThat(member.getProfileImage().getThumbnailUrl()).isEqualTo("thumbnailOne");

        member.modifyMemberInfo(requestTwo);

        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getNickname()).isEqualTo("nicknameTwo");
        assertThat(member.getProfileImage().getOriginalUrl()).isEqualTo("originalOne");
        assertThat(member.getProfileImage().getThumbnailUrl()).isEqualTo("thumbnailOne");
    }
}