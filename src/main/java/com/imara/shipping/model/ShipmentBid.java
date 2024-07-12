package com.imara.shipping.model;

import com.imara.shipping.model.core.AbstractObject;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "[shipment_bids]")
@SequenceGenerator(name = "shipment_bid_seq", allocationSize = 1)
public class ShipmentBid extends AbstractObject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipment_bid_seq")
    private long id;
    private long shipmentId;
    private long driverId;
    private double price;
    @Column(columnDefinition = "text")
    private String note;
    @Enumerated(EnumType.STRING)
    private ShipmentBidStatus status; // OPEN , CLOSE
}
