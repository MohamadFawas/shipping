package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTO;
import lombok.Data;

@Data
public class ShipmentItemDTO extends AbstractDTO {
    private String name;
    private double height;
    private double width;
    private double length;
    private double weight;
    private long shipmentId;
}
