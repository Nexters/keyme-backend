package com.nexters.keyme.member.domain.model;

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
