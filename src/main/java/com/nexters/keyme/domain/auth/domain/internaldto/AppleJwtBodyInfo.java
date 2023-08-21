package com.nexters.keyme.domain.auth.domain.internaldto;

import lombok.Getter;

@Getter
public class AppleJwtBodyInfo {
  private String sub;
  private String email;
}