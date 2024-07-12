package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.ShipmentBid;
import com.imara.shipping.service.DriverService;
import com.imara.shipping.service.ShipmentService;
import com.imara.shipping.utility.TimeZoneConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShipmentBidDTOMapper extends AbstractDTOMapper<ShipmentBid, ShipmentBidDTO> {
    @Autowired
    private TimeZoneConverter tzConverter;
    @Autowired
    private DriverDTOMapper driverDTOMapper;
    @Autowired
    private DriverService driverService;
    @Autowired
    private ShipmentService shipmentService;
    @Autowired
    private ShipmentDTOMapper shipmentDTOMapper;

    @Override
    public ShipmentBid getEntity(ShipmentBidDTO dto) {
        ShipmentBid obj = new ShipmentBid();
        obj.setId(dto.getId());
        obj.setTimestampC(tzConverter.convertFromUTC(dto.getTimestampC()));
        obj.setTimestampU(tzConverter.convertFromUTC(dto.getTimestampU()));
        obj.setCreatedBy(dto.getCreatedBy());
        obj.setUpdatedBy(dto.getUpdatedBy());
        obj.setShipmentId(dto.getShipmentId());
        obj.setDriverId(dto.getDriverId());
        obj.setPrice(dto.getPrice());
        obj.setStatus(dto.getStatus());
        obj.setNote(dto.getNote());
        return obj;
    }

    @Override
    public ShipmentBidDTO getDTO(ShipmentBid obj, int format) {
        ShipmentBidDTO dto = new ShipmentBidDTO();
        dto.setId(obj.getId());
        dto.setTimestampC(obj.getTimestampC());
        dto.setTimestampU(obj.getTimestampU());
        dto.setCreatedBy(obj.getCreatedBy());
        dto.setUpdatedBy(obj.getUpdatedBy());
        dto.setShipmentId(obj.getShipmentId());
        dto.setDriverId(obj.getDriverId());
        dto.setPrice(obj.getPrice());
        dto.setNote(obj.getNote());
        dto.setShipment(shipmentDTOMapper.getDTO(shipmentService.findById(obj.getShipmentId())));
        dto.setDriver(driverDTOMapper.getDTO(driverService.findById(obj.getDriverId())));
        return dto;
    }
}
