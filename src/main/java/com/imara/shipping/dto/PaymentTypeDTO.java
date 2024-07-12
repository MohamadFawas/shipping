package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentTypeDTO extends AbstractDTO {
    private String name;
    private long cardNumber;
    private int cvc;
    private LocalDate expiryDate;
}
