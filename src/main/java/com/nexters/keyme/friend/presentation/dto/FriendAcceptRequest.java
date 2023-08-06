package com.nexters.keyme.friend.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FriendAcceptRequest {
    private Long acceptFriendId;
    private boolean isAccept;
}
