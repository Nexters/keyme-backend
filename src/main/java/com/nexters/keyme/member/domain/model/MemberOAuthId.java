package com.nexters.keyme.member.domain.model;


import com.nexters.keyme.auth.dto.OAuthUserInfo;
import com.nexters.keyme.common.enums.OAuthType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberOAuthId implements Serializable {
    @Column(name = "oauth_type")
    @Enumerated(EnumType.STRING)
    private OAuthType oauthType;

    private String oauthId;

    public MemberOAuthId(OAuthUserInfo oauthUserInfo) {
        this.oauthType = oauthUserInfo.getOauthType();
        this.oauthId = oauthUserInfo.getId();
    }
}
