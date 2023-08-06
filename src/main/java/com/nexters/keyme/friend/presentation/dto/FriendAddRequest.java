package com.nexters.keyme.friend.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FriendAddRequest {
    private Long friendId;
    private String friendCode;
}
