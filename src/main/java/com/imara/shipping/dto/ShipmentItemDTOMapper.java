package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.ShipmentItem;
import com.imara.shipping.utility.TimeZoneConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShipmentItemDTOMapper extends AbstractDTOMapper<ShipmentItem, ShipmentItemDTO> {
    @Autowired
    private TimeZoneConverter tzConverter;

    @Override
    public ShipmentItem getEntity(ShipmentItemDTO dto) {
        if (dto == null) return null;
        ShipmentItem obj = new ShipmentItem();
        obj.setId(dto.getId());
        obj.setTimestampC(tzConverter.convertFromUTC(dto.getTimestampC()));
        obj.setTimestampU(tzConverter.convertFromUTC(dto.getTimestampU()));
        obj.setCreatedBy(dto.getCreatedBy());
        obj.setUpdatedBy(dto.getUpdatedBy());
        obj.setName(dto.getName());
        obj.setHeight(dto.getHeight());
        obj.setWidth(dto.getWidth());
        obj.setLength(dto.getLength());
        obj.setWeight(dto.getWeight());
        obj.setShipmentId(dto.getShipmentId());
        return obj;
    }

    @Override
    public ShipmentItemDTO getDTO(ShipmentItem obj, int format) {
        if (obj == null) return null;
        ShipmentItemDTO dto = new ShipmentItemDTO();
        dto.setId(obj.getId());
        dto.setTimestampC(obj.getTimestampC());
        dto.setTimestampU(obj.getTimestampU());
        dto.setCreatedBy(obj.getCreatedBy());
        dto.setUpdatedBy(obj.getUpdatedBy());
        dto.setName(obj.getName());
        dto.setHeight(obj.getHeight());
        dto.setWidth(obj.getWidth());
        dto.setWeight(obj.getWeight());
        dto.setLength(obj.getLength());
        dto.setWeight(obj.getWeight());
        dto.setShipmentId(obj.getShipmentId());
        return dto;
    }
}
