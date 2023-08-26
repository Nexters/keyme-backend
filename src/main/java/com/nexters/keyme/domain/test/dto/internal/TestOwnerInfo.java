package com.nexters.keyme.domain.test.dto.internal;


import com.nexters.keyme.domain.member.domain.model.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestOwnerInfo {
    private Long id;
    private String nickname;
    private String profileThumbnail;

    public TestOwnerInfo(MemberEntity member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.profileThumbnail = member.getProfileImage().getThumbnailUrl();
    }
}
