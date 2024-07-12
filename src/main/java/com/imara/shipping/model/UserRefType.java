package com.imara.shipping.model;

import lombok.Getter;

public enum UserRefType {

  MASTER_ADMIN(1, "MASTER_ADMIN"),
  ADMIN(2, "ADMIN"),
  REP(3, "REP"),
  REGIONAL_REP(4, "REGIONAL_REP");

  @Getter
  private int id;

  @Getter
  private String description;

  UserRefType(int id, String description) {
    this.id = id;
    this.description = description;
  }

}