package com.nexters.keyme.global.common.enums;

public enum AuthRole {
  ANONYMOUS,
  USER,
  ADMIN;

  public String role() {
    return "ROLE_" + this.name();
  }
}
