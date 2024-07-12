package com.imara.shipping.model;

import com.imara.shipping.model.core.AbstractObject;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "[shipments]")
@SequenceGenerator(name = "shipment_seq", allocationSize = 1)
public class Shipment extends AbstractObject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipment_seq")
    private long id;
    private String category;
    private String note;

    @Enumerated(EnumType.STRING)
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
    private boolean autoSelect;
    @Transient
    private List<ShipmentItem> items = new ArrayList<>();
}


