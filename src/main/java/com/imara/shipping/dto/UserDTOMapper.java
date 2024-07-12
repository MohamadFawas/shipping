package com.imara.shipping.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.User;
import com.imara.shipping.utility.TimeZoneConverter;

@Component
public class UserDTOMapper extends AbstractDTOMapper<User, UserDTO> {

  @Autowired
  private TimeZoneConverter tzConverter;

  @Override
  public User getEntity(UserDTO dto) {
    if (dto == null) return null;
    User obj = new User();
    obj.setId(dto.getId());
    obj.setTimestampC(tzConverter.convertFromUTC(dto.getTimestampC()));
    obj.setTimestampU(tzConverter.convertFromUTC(dto.getTimestampU()));
    obj.setCreatedBy(dto.getCreatedBy());
    obj.setUpdatedBy(dto.getUpdatedBy());
    obj.setFullName(dto.getFullName());
    obj.setPassword(dto.getPassword());
    obj.setPasswordN(dto.getPasswordN());
    obj.setActive(dto.isActive());
    obj.setUserRole(dto.getUserRole());
    obj.setPhoneNumber(dto.getPhoneNumber());
    obj.setUsername(dto.getPhoneNumber());
    obj.setRefId(dto.getRefId());
    return obj;
  }

  @Override
  public UserDTO getDTO(User obj, int format) {
    if (obj == null) return null;
    //
    UserDTO dto = new UserDTO();
    if(format == UserDTO.OVERVIEW)
    {
      dto.setId(obj.getId());
      dto.setTimestampC(obj.getTimestampC());
      dto.setTimestampU(obj.getTimestampU());
      dto.setCreatedBy(obj.getCreatedBy());
      dto.setUpdatedBy(obj.getUpdatedBy());
      dto.setFullName(obj.getFullName());
      dto.setUserRole(obj.getUserRole());
      dto.setPhoneNumber(obj.getPhoneNumber());
      dto.setUserName(obj.getUsername());
      dto.setRefId(obj.getRefId());
    }
    else
    {
      dto.setId(obj.getId());
      dto.setTimestampC(obj.getTimestampC());
      dto.setTimestampU(obj.getTimestampU());
      dto.setCreatedBy(obj.getCreatedBy());
      dto.setUpdatedBy(obj.getUpdatedBy());
      dto.setFullName(obj.getFullName());
      dto.setPassword(obj.getPassword());
      dto.setActive(obj.isActive());
      dto.setUserRole(obj.getUserRole());
      dto.setPhoneNumber(obj.getPhoneNumber());
      dto.setUserName(obj.getUsername());
      dto.setRefId(obj.getRefId());
    }
    return dto;
  }
    
}
