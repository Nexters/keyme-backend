package com.nexters.keyme.domain.auth.domain.internaldto;

import com.nexters.keyme.global.enums.OAuthType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OAuthUserInfo {
  private String id;
  private OAuthType oauthType;
}
