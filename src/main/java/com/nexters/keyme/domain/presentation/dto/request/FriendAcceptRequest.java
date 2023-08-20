package com.nexters.keyme.domain.presentation.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FriendAcceptRequest {
    @ApiModelProperty(value="수락할 친구 ID", example = "22")
    private Long acceptFriendId;
    @ApiModelProperty(value="수락 여부", example = "true")
    private boolean isAccept;
}
