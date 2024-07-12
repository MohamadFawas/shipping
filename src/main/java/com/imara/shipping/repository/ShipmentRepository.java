package com.imara.shipping.repository;

import com.imara.shipping.model.Shipment;
import com.imara.shipping.model.ShipmentStatus;
import com.imara.shipping.repository.core.AbstractRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipmentRepository extends AbstractRepository<Shipment, Long> {
    List<Shipment> findAllByCustomerId(long id);

    List<Shipment> findAllByDriverId(long id);

    List<Shipment> findAllByDriverIdAndStatus(long id, ShipmentStatus status);

    List<Shipment> findAllByCustomerIdAndStatus(long id, ShipmentStatus status);


    @Modifying
    @Query("UPDATE Shipment s SET s.status = :status WHERE s.id = :id")
    void updateStatusById(long id, ShipmentStatus status);

    @Modifying
    @Query("UPDATE Shipment s SET s.deliveryCost = :cost WHERE s.id = :id")
    void updateDeliveryCostByID(long id, double cost);

    List<Shipment> findByStatus(ShipmentStatus status);

    @Modifying
    @Query("UPDATE Shipment s SET s.driverId = :driverId WHERE s.id = :id")
    void updateDriverIdById(long id, long driverId);

    @Modifying
    @Query("UPDATE Shipment s SET s.driverId = :driverId , s.status = :status WHERE s.id = :id")
    void updateDriverIdAndStatusById(long id, ShipmentStatus status, long driverId);
}
