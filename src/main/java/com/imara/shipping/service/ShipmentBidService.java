package com.imara.shipping.service;

import com.imara.shipping.model.ShipmentBid;
import com.imara.shipping.model.ShipmentBidStatus;
import com.imara.shipping.model.ShipmentStatus;
import com.imara.shipping.repository.ShipmentBidRepository;
import com.imara.shipping.repository.core.AbstractRepository;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class ShipmentBidService extends AbstractService<ShipmentBid> {
    @Autowired
    protected ShipmentService shipmentService;
    @Autowired
    private ShipmentBidRepository shipmentBidRepository;

    @Override
    protected AbstractRepository getRepository() {
        return shipmentBidRepository;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    public List<ShipmentBid> getAllByShipmentIdAndStatus(long shipmentId, ShipmentBidStatus status) {
        return shipmentBidRepository.getAllByShipmentIdAndStatus(shipmentId, status);
    }

    public List<ShipmentBid> getAllByDriverIdAndStatus(long shipmentId, ShipmentBidStatus status) {
        return shipmentBidRepository.getAllByDriverIdAndStatus(shipmentId, status);
    }

    public void updateStatusById(long id, ShipmentBidStatus status) {
        boolean bidExists = shipmentBidRepository.findById(id).isPresent();
        if (bidExists) {
            shipmentBidRepository.updateStatusById(id, status);
        }

    }

    public void acceptBid(long shipmentId, long bidId) {
        updateStatusById(bidId, ShipmentBidStatus.ACCEPTED);
        ShipmentBid selectedBid = findById(bidId);
        Iterator<ShipmentBid> rejectedBids = getAllByShipmentIdAndStatus(shipmentId, ShipmentBidStatus.PENDING).iterator();
        shipmentService.updateStatusById(shipmentId, ShipmentStatus.DRIVER_ASSIGNED);
        shipmentService.updateCostById(shipmentId, selectedBid.getPrice());
        shipmentService.updateDriverIdByName(shipmentId, selectedBid.getDriverId());
        while (rejectedBids.hasNext()) {
            ShipmentBid bid = rejectedBids.next();
            updateStatusById(bid.getId(), ShipmentBidStatus.REJECTED);
        }
    }

    public List<ShipmentBid> getAllByDriverId(long driverId) {
        return shipmentBidRepository.getAllByDriverId(driverId);
    }

    public List<ShipmentBid> getAllByShipmentId(long shipmentId) {
        return shipmentBidRepository.getAllByShipmentId(shipmentId);
    }
}
