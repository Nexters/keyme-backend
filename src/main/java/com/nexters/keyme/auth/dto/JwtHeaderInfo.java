package com.nexters.keyme.auth.dto;

import lombok.Getter;

@Getter
public class JwtHeaderInfo {
  private String kid;
  private String alg;
}
