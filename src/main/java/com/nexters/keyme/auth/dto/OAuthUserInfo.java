package com.nexters.keyme.auth.dto;

import com.nexters.keyme.common.enums.OAuthType;
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
