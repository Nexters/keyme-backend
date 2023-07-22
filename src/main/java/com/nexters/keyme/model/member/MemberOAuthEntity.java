package com.nexters.keyme.model.member;

import com.nexters.keyme.common.enums.OAuthType;
import com.nexters.keyme.model.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "member_oauth")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberOAuthEntity {
    @EmbeddedId
    private MemberOAuthId oauthInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Builder
    public MemberOAuthEntity(MemberOAuthId oauthInfo, MemberEntity member) {
        this.oauthInfo = oauthInfo;
        this.member = member;
    }
}
