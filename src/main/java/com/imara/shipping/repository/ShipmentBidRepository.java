package com.imara.shipping.repository;

import com.imara.shipping.model.ShipmentBid;
import com.imara.shipping.model.ShipmentBidStatus;
import com.imara.shipping.repository.core.AbstractRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipmentBidRepository extends AbstractRepository<ShipmentBid, Long> {
    List<ShipmentBid> getAllByShipmentIdAndStatus(long shipmentId, ShipmentBidStatus status);

    List<ShipmentBid> getAllByDriverIdAndStatus(long driverId, ShipmentBidStatus status);

    @Modifying
    @Query("UPDATE ShipmentBid sb SET sb.status = :status WHERE sb.id = :id")
    void updateStatusById(long id, ShipmentBidStatus status);

    List<ShipmentBid> getAllByDriverId(long driverId);

    List<ShipmentBid> getAllByShipmentId(long shipmentId);
}
