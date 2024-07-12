package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.City;
import com.imara.shipping.utility.TimeZoneConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityDTOMapper extends AbstractDTOMapper<City,CityDTO> {
    @Autowired
    private TimeZoneConverter tzConverter;
    @Override
    public City getEntity(CityDTO dto) {
        if (dto == null) return null;
        City obj = new City();
        obj.setId(dto.getId());
        obj.setTimestampC(tzConverter.convertFromUTC(dto.getTimestampC()));
        obj.setTimestampU(tzConverter.convertFromUTC(dto.getTimestampU()));
        obj.setCreatedBy(dto.getCreatedBy());
        obj.setUpdatedBy(dto.getUpdatedBy());
        obj.setEnglishName(dto.getEnglishName());
        obj.setArabicName(dto.getArabicName());
        obj.setPostcode(dto.getPostCode());
        return obj;
    }
    @Override
    public CityDTO getDTO(City obj, int format) {
        if (obj == null) return null;
        CityDTO dto = new CityDTO();
        dto.setId(obj.getId());
        dto.setTimestampC(obj.getTimestampC());
        dto.setTimestampU(obj.getTimestampU());
        dto.setCreatedBy(obj.getCreatedBy());
        dto.setUpdatedBy(obj.getUpdatedBy());
        dto.setEnglishName(obj.getEnglishName());
        dto.setArabicName(obj.getArabicName());
        dto.setPostCode(obj.getPostcode());
        return dto;
    }
}
