package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTO;

import lombok.Data;

@Data
public class ItemPriceDTO extends AbstractDTO{
	
	private long id;
	private double price; 
	private long shipmentItemId;
}
