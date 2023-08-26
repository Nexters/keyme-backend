package com.nexters.keyme.domain.auth.dto.internal;

import lombok.Getter;

@Getter
public class AppleJwtBodyInfo {
  private String sub;
  private String email;
}