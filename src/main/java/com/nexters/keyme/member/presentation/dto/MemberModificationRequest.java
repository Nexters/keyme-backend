package com.nexters.keyme.member.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberModificationRequest {
    private String nickname;
    private String image;
}
