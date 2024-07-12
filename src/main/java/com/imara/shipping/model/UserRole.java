package com.imara.shipping.model;

import lombok.Getter;

public enum UserRole {

  MASTER_ADMIN(1, "MASTER_ADMIN"),
  COMPANY(2, "COMPANY"),
  CUSTOMER(3, "CUSTOMER"),
  DRIVER(4, "DRIVER");

  @Getter
  private int id;

  @Getter
  private String description;

  UserRole(int id, String description) {
    this.id = id;
    this.description = description;
  }

}