package com.imara.shipping.model;

import com.imara.shipping.model.core.AbstractObject;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[shipment_items]")
@Data
@SequenceGenerator(name = "shipment_item_seq", allocationSize = 1)
public class ShipmentItem extends AbstractObject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipment_item_seq")
    private long id;
    private String name;
    private double height;
    private double width;
    private double length;
    private double weight;
    private long shipmentId;
}
