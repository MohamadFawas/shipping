package com.imara.shipping.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.imara.shipping.model.core.AbstractObject;

import lombok.Data;

@Data
@Entity
@Table(name = "[item_prices]")
@SequenceGenerator(name = "item_price_seq", allocationSize = 1)
public class ItemPrice extends AbstractObject {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_price_seq")
    private long id;
	private double price;  
	@ManyToOne
	@JoinColumn(name = "shipment_item_id", nullable = false)
	private ShipmentItem shipmentItem;
}
