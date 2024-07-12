package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTO;
import lombok.Data;

@Data
public class UserCustomerRequest extends AbstractDTO {
        private UserDTO user;
        private CustomerDTO customer;
}
