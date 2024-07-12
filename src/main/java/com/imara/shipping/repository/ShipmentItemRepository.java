package com.imara.shipping.repository;

import com.imara.shipping.model.ShipmentItem;
import com.imara.shipping.repository.core.AbstractRepository;

import java.util.List;

public interface ShipmentItemRepository extends AbstractRepository<ShipmentItem, Long> {
    List<ShipmentItem> findAllByShipmentId(long id);
}
