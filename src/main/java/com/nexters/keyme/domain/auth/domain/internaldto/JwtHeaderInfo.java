package com.nexters.keyme.domain.auth.domain.internaldto;

import lombok.Getter;

@Getter
public class JwtHeaderInfo {
  private String kid;
  private String alg;
}
