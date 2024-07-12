package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO extends AbstractDTO {
    private String name;
    private long paymentTypeId;
    private double price;
    private LocalDateTime date;
    private long shipmentId;
    private long clientId;
}
