package com.nexters.keyme.auth.domain.internaldto;

import lombok.Getter;

@Getter
public class AppleJwtBodyInfo {
  private String sub;
  private String email;
}