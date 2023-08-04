package com.nexters.keyme.member.presentation.dto.response;

import com.nexters.keyme.member.domain.model.MemberEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class MemberResponse {
    @NotNull
    @ApiModelProperty(value="유저 고유 ID")
    private Long id;
    @NotNull
    @ApiModelProperty(value="닉네임")
    private String nickname;
    @NotNull
    @ApiModelProperty(value="친구코드")
    private String friendCode;
    @ApiModelProperty(value="프로필 원본 이미지 URL")
    private String profileImage;
    @ApiModelProperty(value="프로필 섬네일 이미지 URL")
    private String profileTumbnail;

    public MemberResponse(MemberEntity member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.friendCode = member.getFriendCode();
        this.profileImage = member.getProfileImage().getOriginalUrl();
        this.profileTumbnail = member.getProfileImage().getThumbnailUrl();
    }
}
