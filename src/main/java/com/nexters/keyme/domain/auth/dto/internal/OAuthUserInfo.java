package com.nexters.keyme.domain.auth.dto.internal;

import com.nexters.keyme.domain.auth.enums.OAuthType;
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
