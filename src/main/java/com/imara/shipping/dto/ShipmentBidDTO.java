package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTO;
import com.imara.shipping.model.ShipmentBidStatus;
import lombok.Data;

@Data
public class ShipmentBidDTO extends AbstractDTO {
    private long shipmentId;
    private long driverId;
    private double price;
    private String note;
    private ShipmentBidStatus status;
    private ShipmentDTO shipment;
    private DriverDTO driver;
}
