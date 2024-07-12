package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.PaymentType;
import com.imara.shipping.utility.TimeZoneConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentTypeDTOMapper extends AbstractDTOMapper<PaymentType, PaymentTypeDTO> {
    @Autowired
    private TimeZoneConverter tzConverter;

    @Override
    public PaymentType getEntity(PaymentTypeDTO dto) {
        PaymentType obj = new PaymentType();
        if (dto == null) {
            return null;
        }
        obj.setId(dto.getId());
        obj.setTimestampC(tzConverter.convertFromUTC(dto.getTimestampC()));
        obj.setTimestampU(tzConverter.convertFromUTC(dto.getTimestampU()));
        obj.setCreatedBy(dto.getCreatedBy());
        obj.setUpdatedBy(dto.getUpdatedBy());
        obj.setName(dto.getName());
        obj.setCardNumber(dto.getCardNumber());
        obj.setCvc(dto.getCvc());
        obj.setExpiryDate(dto.getExpiryDate());
        return obj;
    }

    @Override
    public PaymentTypeDTO getDTO(PaymentType obj, int format) {
        PaymentTypeDTO dto = new PaymentTypeDTO();
        if (obj == null) {
            return null;
        }
        dto.setId(obj.getId());
        dto.setTimestampC(obj.getTimestampC());
        dto.setTimestampU(obj.getTimestampU());
        dto.setCreatedBy(obj.getCreatedBy());
        dto.setUpdatedBy(obj.getUpdatedBy());
        dto.setName(obj.getName());
        dto.setCardNumber(obj.getCardNumber());
        dto.setCvc(obj.getCvc());
        dto.setExpiryDate(obj.getExpiryDate());
        return dto;
    }
}
