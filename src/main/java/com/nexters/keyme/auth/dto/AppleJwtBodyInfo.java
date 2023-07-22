package com.nexters.keyme.auth.dto;

import lombok.Getter;

@Getter
public class AppleJwtBodyInfo {
  private String sub;
  private String email;
}