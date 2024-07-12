package com.imara.shipping.service;

import com.imara.shipping.model.ShipmentItem;
import com.imara.shipping.repository.ShipmentItemRepository;
import com.imara.shipping.repository.core.AbstractRepository;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ShipmentItemService extends AbstractService<ShipmentItem> {
    @Autowired
    private ShipmentItemRepository shipmentItemRepository;

    @Override
    protected AbstractRepository getRepository() {
        return shipmentItemRepository;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    public List<ShipmentItem> findAllByShipmentId(long id) {
        return shipmentItemRepository.findAllByShipmentId(id);
    }
}
