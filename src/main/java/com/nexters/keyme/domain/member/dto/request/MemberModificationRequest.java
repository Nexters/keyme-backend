package com.nexters.keyme.domain.member.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberModificationRequest {
    @ApiModelProperty(value="닉네임", example = "키미")
    @NotNull(message = "닉네임을 입력해주세요.")
    private String nickname;
    @ApiModelProperty(value="프로필 이미지 URL")
    @NotNull(message = "프로필 이미지가 유효하지 않습니다.")
    private String profileImage;
    @ApiModelProperty(value="프로필 이미지 섬네일 URL")
    @NotNull(message = "섬네일 이미지가 유효하지 않습니다.")
    private String profileThumbnail;
}
