package com.nexters.keyme.member.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberModificationRequest {
    private String nickname;
    private String profileImage;
    private String profileThumbnail;
}
