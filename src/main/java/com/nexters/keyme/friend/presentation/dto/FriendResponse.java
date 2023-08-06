package com.nexters.keyme.friend.presentation.dto;

import lombok.*;

@Getter
@RequiredArgsConstructor
@Builder
public class FriendResponse {
    private final Long memberId;
    private final String nickname;
    private final String thumbnailUrl;
}
