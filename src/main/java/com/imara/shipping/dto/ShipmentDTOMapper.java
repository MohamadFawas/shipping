package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.Shipment;
import com.imara.shipping.model.ShipmentItem;
import com.imara.shipping.repository.StatusUpdatedTimeRepository;
import com.imara.shipping.service.*;
import com.imara.shipping.utility.TimeZoneConverter;
import com.imara.shipping.utility.ValueCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShipmentDTOMapper extends AbstractDTOMapper<Shipment, ShipmentDTO> {
    @Autowired
    AdminPreferenceService adminPreferenceService;
    @Autowired
    ShipmentItemService shipmentItemService;
    @Autowired
    CustomerDTOMapper customerDTOMapper;
    @Autowired
    CustomerService customerService;

    @Autowired
    private StatusUpdatedTimeRepository statusUpdatedTimeRepository;
    @Autowired
    DriverDTOMapper driverDTOMapper;

    @Autowired
    DriverService driverService;
    @Autowired
    ApprovalDTOMapper approvalDTOMapper;
    @Autowired
    ApprovalService approvalService;
    @Autowired
    ShipmentItemDTOMapper shipmentItemDTOMapper;
    @Autowired
    private TimeZoneConverter tzConverter;

    @Override
    public Shipment getEntity(ShipmentDTO dto) {
        if (dto == null) return null;
        Shipment obj = new Shipment();
        obj.setId(dto.getId());
        obj.setTimestampC(tzConverter.convertFromUTC(dto.getTimestampC()));
        obj.setTimestampU(tzConverter.convertFromUTC(dto.getTimestampU()));
        obj.setCreatedBy(dto.getCreatedBy());
        obj.setUpdatedBy(dto.getUpdatedBy());
        obj.setDeliveryCost(dto.getDeliveryCost());
        obj.setDate(dto.getDate());
        obj.setRecipientName(dto.getRecipientName());
        obj.setCustomerId(dto.getCustomerId());
        obj.setDriverId(dto.getDriverId());
        obj.setNote(dto.getNote());
        obj.setRecipientPhoneNumber(dto.getRecipientPhoneNumber());
        obj.setPaymentId(dto.getPaymentId());
        obj.setDropOffAddress(dto.getDropOffAddress());
        obj.setDropOffLatitude(dto.getDropOffLatitude());
        obj.setDropOffLongitude(dto.getDropOffLongitude());
        obj.setPickupAddress(dto.getPickupAddress());
        obj.setPickupLatitude(dto.getPickupLatitude());
        obj.setPickupLongitude(dto.getPickupLongitude());
        obj.setStatus(dto.getStatus());
        obj.setAutoSelect(dto.isAutoSelect());
        for (ShipmentItemDTO itemDTO : dto.getItems()) {
            obj.getItems().add(shipmentItemDTOMapper.getEntity(itemDTO));
        }
        return obj;
    }
        @Override
        public ShipmentDTO getDTO (Shipment obj,int format) {
            if (obj == null) return null;
            //
            ShipmentDTO dto = new ShipmentDTO();
            dto.setId(obj.getId());
            dto.setTimestampC(obj.getTimestampC());
            dto.setTimestampU(obj.getTimestampU());
            dto.setCreatedBy(obj.getCreatedBy());
            dto.setUpdatedBy(obj.getUpdatedBy());
            dto.setNote(obj.getNote());
            dto.setCustomerId(obj.getCustomerId());
            dto.setDriverId(obj.getDriverId());
            dto.setDate(obj.getDate());
            dto.setDropOffAddress(obj.getDropOffAddress());
            dto.setDropOffLatitude(obj.getDropOffLatitude());
            dto.setDropOffLongitude(obj.getDropOffLongitude());
            dto.setStatus(obj.getStatus());
            dto.setPickupAddress(obj.getPickupAddress());
            dto.setPickupLatitude(obj.getPickupLatitude());
            dto.setPickupLongitude(obj.getPickupLongitude());
            dto.setPaymentId(obj.getPaymentId());
            dto.setRecipientName(obj.getRecipientName());
            dto.setRecipientPhoneNumber(obj.getRecipientPhoneNumber());
            dto.setDeliveryCost(obj.getDeliveryCost());
            dto.setCustomer(customerDTOMapper.getDTO(customerService.findById(obj.getCustomerId())));
            dto.setDriver(driverDTOMapper.getDTO(driverService.findById(obj.getDriverId())));
            dto.setApproval(approvalDTOMapper.getDTO(approvalService.findByShipmentId(obj.getId())));
            dto.setStatusUpdatedTime(statusUpdatedTimeRepository.findByShipmentId(obj.getId()));
            dto.setAutoSelect(obj.isAutoSelect());
            for (ShipmentItem item : shipmentItemService.findAllByShipmentId(obj.getId())) {
                dto.getItems().add(shipmentItemDTOMapper.getDTO(item));
            }
            long percentage = Long.parseLong(adminPreferenceService.findByName("SHIPMENT_PERCENTAGE").getValue());
            dto.setMinimumBidValue(ValueCalculator.calculateCompanyShare(obj.getDeliveryCost(), percentage));

            return dto;
        }
}