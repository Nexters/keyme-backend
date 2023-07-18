package com.nexters.keyme.auth.dto;

import lombok.Getter;

@Getter
public class JwtHeader {
  private String kid;
  private String alg;
}
