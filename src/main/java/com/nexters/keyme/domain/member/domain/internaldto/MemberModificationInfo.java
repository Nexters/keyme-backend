package com.nexters.keyme.domain.member.domain.internaldto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberModificationInfo {
    private String nickname;
    private String originalImage;
    private String thumbnailImage;
}
