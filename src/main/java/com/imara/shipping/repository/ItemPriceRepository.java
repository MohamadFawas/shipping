package com.imara.shipping.repository;

import java.util.List;

import com.imara.shipping.model.ItemPrice;
import com.imara.shipping.repository.core.AbstractRepository;

public interface ItemPriceRepository extends AbstractRepository<ItemPrice,Long>{
	
	List<ItemPrice> findAllByShipmentItemId(long shipmentItemId);
	boolean existsByShipmentItemId(long shipmentItemId);
}
