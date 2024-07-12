package com.imara.shipping.service;

import com.imara.shipping.dto.CalculateDTO;
import com.imara.shipping.model.*;
import com.imara.shipping.repository.ShipmentRepository;
import com.imara.shipping.repository.StatusUpdatedTimeRepository;
import com.imara.shipping.repository.core.AbstractRepository;
import com.imara.shipping.service.core.AbstractService;
import com.imara.shipping.utility.ValueCalculator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class ShipmentService extends AbstractService<Shipment> {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ShipmentItemService shipmentItemService;
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private AdminPreferenceService adminPreferenceService;
    @Autowired
    private StatusUpdatedTimeRepository statusUpdatedTimeRepository;


    @Override
    protected AbstractRepository getRepository() {
        return shipmentRepository;
    }

    @Override
    protected Logger getLog() {
        return log;
    }



    @Override
    public Shipment save(Shipment shipment) {
        shipment.setStatus(ShipmentStatus.WAITING_ACCEPTANCE);
        Shipment savedShipment = shipmentRepository.save(shipment);
        for (ShipmentItem item : shipment.getItems()) {
            item.setShipmentId(savedShipment.getId());
            shipmentItemService.save(item);
        }
        StatusUpdatedTime statusUpdatedTime = new StatusUpdatedTime();
        statusUpdatedTime.setShipmentId(savedShipment.getId());
        statusUpdatedTime.setWaitingAcceptance(LocalDateTime.now());
        statusUpdatedTimeRepository.save(statusUpdatedTime);
        return savedShipment;
    }

    public List<Shipment> findByCustomerId(long id) {
        return shipmentRepository.findAllByCustomerId(id);
    }

    public List<Shipment> findByDriverId(long id) {
        return shipmentRepository.findAllByDriverId(id);
    }


    public List<Shipment> findByDriverIdAndStatus(long id, ShipmentStatus status) {
        return shipmentRepository.findAllByDriverIdAndStatus(id, status);
    }

    public List<Shipment> findByCustomerIdAndStatus(long id, ShipmentStatus status) {
        return shipmentRepository.findAllByCustomerIdAndStatus(id, status);
    }


    public void updateStatusById(long id, ShipmentStatus status) {
        boolean shipmentExists = shipmentRepository.findById(id).isPresent();
        if (shipmentExists) {
            shipmentRepository.updateStatusById(id, status);
            StatusUpdatedTime statusUpdatedTime = statusUpdatedTimeRepository.findByShipmentId(id);
            LocalDateTime currentTime = LocalDateTime.now();

            switch (status) {
                case WAITING_ACCEPTANCE:
                    statusUpdatedTime.setWaitingAcceptance(currentTime);
                    break;
                case DRIVER_ASSIGNED:
                    statusUpdatedTime.setDriverAccepted(currentTime);
                    break;
                case WAITING_PICKUP_CONFIRMATION:
                    statusUpdatedTime.setWaitingPickupConfirmation(currentTime);
                    break;
                case PICKUP_CONFIRMED:
                    statusUpdatedTime.setPickUpConfirmed(currentTime);
                    break;
                case SHIPMENT_STARTED:
                    statusUpdatedTime.setShipmentStarted(currentTime);
                    break;
                case SHIPMENT_COMPLETED:
                    statusUpdatedTime.setShipmentCompleted(currentTime);
                    break;
                default:
                    break;
            }

            statusUpdatedTimeRepository.save(statusUpdatedTime);
        }
    }

    public CalculateDTO calculatePrice(CalculateDTO calculateDTO) {
        AdminPreference shipmentRate = adminPreferenceService.findByName("SHIPMENT_RATE");
        double price = Double.parseDouble(shipmentRate.getValue());
        double distance = ValueCalculator.calculateDistance(calculateDTO.getPickupLatitude(), calculateDTO.getPickupLongitude(), calculateDTO.getDropOffLatitude(), calculateDTO.getDropOffLongitude());
        double weight = calculateDTO.getWeight();
        calculateDTO.setPrice(price * distance * weight);
        calculateDTO.setDistance(distance);
        return calculateDTO;

    }

    public void updateCostById(long id, double cost) {
        shipmentRepository.updateDeliveryCostByID(id, cost);
    }


    public List<Shipment> findByStatus(ShipmentStatus status) {
        return shipmentRepository.findByStatus(status);
    }

    public void updateDriverIdByName(long shipmentId, long driverId) {
        shipmentRepository.updateDriverIdById(shipmentId, driverId);
    }

    public void autoSelect(long id, long driverId) {
        shipmentRepository.updateDriverIdAndStatusById(id, ShipmentStatus.DRIVER_ASSIGNED, driverId);
    }

    public List<Shipment> getAllByCity(long cityId) {
        Iterator<Customer> customers = customerService.findAllByCityId(cityId).iterator();
        List<Shipment> shipments = new ArrayList<>();
        while (customers.hasNext()) {
            Customer customer = customers.next();
            List<Shipment> customerShiments = shipmentRepository.findAllByCustomerId(customer.getId());
            shipments.addAll(customerShiments);
        }
        return shipments;
    }
}
