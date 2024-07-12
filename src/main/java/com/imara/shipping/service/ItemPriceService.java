package com.imara.shipping.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imara.shipping.model.ItemPrice;
import com.imara.shipping.repository.ItemPriceRepository;
import com.imara.shipping.repository.core.AbstractRepository;
import com.imara.shipping.service.core.AbstractService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ItemPriceService extends AbstractService<ItemPrice> {
	
	@Autowired
	private ItemPriceRepository itemPriceRepository;

	@Override
	protected AbstractRepository getRepository() {
		return itemPriceRepository;
	}

	@Override
	protected Logger getLog() {
		return log;
	}
	
	public List<ItemPrice> findAllByShipmentItemId(long shipmentItemId){
		return itemPriceRepository.findAllByShipmentItemId(shipmentItemId);
	}
	
	public boolean isItemPriceExists(long shipmentItemId) {
		return itemPriceRepository.existsByShipmentItemId(shipmentItemId);
	}
}
