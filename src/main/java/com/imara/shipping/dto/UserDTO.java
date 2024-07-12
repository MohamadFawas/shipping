package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTO;
import com.imara.shipping.model.UserRefType;
import com.imara.shipping.model.UserRole;

import lombok.Data;

@Data
public class UserDTO extends AbstractDTO {

  private String fullName;
  private String password;
  private String passwordN;
  private boolean active;
  private UserRole userRole;
  private String phoneNumber;
  private String userName;
  private long refId;

}
