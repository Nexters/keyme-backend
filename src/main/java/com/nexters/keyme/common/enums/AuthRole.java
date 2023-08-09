package com.nexters.keyme.common.enums;

public enum AuthRole {
  ANONYMOUS,
  USER,
  ADMIN;

  public String role() {
    return "ROLE_" + this.name();
  }
}
