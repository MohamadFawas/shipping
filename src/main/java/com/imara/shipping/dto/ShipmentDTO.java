package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTO;
import com.imara.shipping.model.ShipmentStatus;
import com.imara.shipping.model.StatusUpdatedTime;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShipmentDTO extends AbstractDTO {
    private String note;
    private ShipmentStatus status;
    private long customerId;
    private long driverId;
    private String pickupAddress;
    private double pickupLatitude;
    private double pickupLongitude;
    private String dropOffAddress;
    private double dropOffLatitude;
    private double dropOffLongitude;
    private long paymentId;
    private double deliveryCost;
    private String recipientName;
    private String recipientPhoneNumber;
    private LocalDate Date;
    private DriverDTO driver;
    private ApprovalDTO approval;
    private CustomerDTO customer;
    private boolean autoSelect;
    private StatusUpdatedTime statusUpdatedTime;
    private List<ShipmentItemDTO> items = new ArrayList<>();
    private double minimumBidValue;
}
