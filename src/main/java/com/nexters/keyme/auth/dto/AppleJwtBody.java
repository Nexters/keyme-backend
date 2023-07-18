package com.nexters.keyme.auth.dto;

import lombok.Getter;

@Getter
public class AppleJwtBody {
  private String sub;
  private String email;
}