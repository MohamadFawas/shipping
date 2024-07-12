package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTO;
import lombok.Data;

@Data
public class CustomerDTO extends AbstractDTO {


    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private long cityId;
    private String fullName;
    private String passwordN;
    private String address;
    private CityDTO city;
}
