package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.Payment;
import com.imara.shipping.utility.TimeZoneConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentDTOMapper extends AbstractDTOMapper<Payment, PaymentDTO> {
    @Autowired
    private TimeZoneConverter tzConverter;

    @Override
    public Payment getEntity(PaymentDTO dto) {
        Payment obj = new Payment();
        if (dto == null) {
            return null;
        }
        obj.setId(dto.getId());
        obj.setTimestampC(tzConverter.convertFromUTC(dto.getTimestampC()));
        obj.setTimestampU(tzConverter.convertFromUTC(dto.getTimestampU()));
        obj.setCreatedBy(dto.getCreatedBy());
        obj.setUpdatedBy(dto.getUpdatedBy());
        obj.setName(dto.getName());
        obj.setShipmentId(dto.getShipmentId());
        obj.setDate(dto.getDate());
        obj.setPrice(dto.getPrice());
        obj.setPaymentTypeId(dto.getPaymentTypeId());
        obj.setClientId(dto.getClientId());
        return obj;
    }

    @Override
    public PaymentDTO getDTO(Payment obj, int format) {
        PaymentDTO dto = new PaymentDTO();
        if (obj == null) {
            return null;
        }
        dto.setId(obj.getId());
        dto.setTimestampC(obj.getTimestampC());
        dto.setTimestampU(obj.getTimestampU());
        dto.setCreatedBy(obj.getCreatedBy());
        dto.setUpdatedBy(obj.getUpdatedBy());
        dto.setName(obj.getName());
        dto.setPrice(obj.getPrice());
        dto.setClientId(obj.getClientId());
        dto.setPaymentTypeId(obj.getPaymentTypeId());
        dto.setDate(obj.getDate());
        dto.setShipmentId(obj.getShipmentId());
        return dto;
    }
}
