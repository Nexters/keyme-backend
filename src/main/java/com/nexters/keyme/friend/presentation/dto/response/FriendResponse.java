package com.nexters.keyme.friend.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@RequiredArgsConstructor
@Builder
public class FriendResponse {
    @ApiModelProperty(value="멤버 ID", example = "22")
    private final Long memberId;
    @ApiModelProperty(value="닉네임", example = "키미")
    private final String nickname;
    @ApiModelProperty(value="섬네일 URL", example = "thumbnail url")
    private final String thumbnailUrl;
}
