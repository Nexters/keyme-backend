package com.nexters.keyme.domain.member.domain.model;


import com.nexters.keyme.domain.auth.dto.internal.OAuthUserInfo;
import com.nexters.keyme.global.common.enums.OAuthType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
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
