package com.nexters.keyme.member.presentation.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberModificationRequest {
    @ApiModelProperty(value="닉네임", example = "키미")
    private String nickname;
    @ApiModelProperty(value="프로필 이미지 URL")
    private String profileImage;
    @ApiModelProperty(value="프로필 이미지 섬네일 URL")
    private String profileThumbnail;
}
