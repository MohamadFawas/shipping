package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.AdminPreference;
import com.imara.shipping.utility.TimeZoneConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminPreferenceDTOMapper extends AbstractDTOMapper<AdminPreference, AdminPreferenceDTO> {

    @Autowired
    private TimeZoneConverter tzConverter;

    @Override
    public AdminPreference getEntity(AdminPreferenceDTO dto) {
        if (dto == null) return null;
        AdminPreference obj = new AdminPreference();
        obj.setId(dto.getId());
        obj.setTimestampC(tzConverter.convertFromUTC(dto.getTimestampC()));
        obj.setTimestampU(tzConverter.convertFromUTC(dto.getTimestampU()));
        obj.setCreatedBy(dto.getCreatedBy());
        obj.setUpdatedBy(dto.getUpdatedBy());
        obj.setName(dto.getName());
        obj.setValue(dto.getValue());
        return obj;
    }

    @Override
    public AdminPreferenceDTO getDTO(AdminPreference obj, int format) {
        if (obj == null) return null;
        AdminPreferenceDTO dto = new AdminPreferenceDTO();
        if (format == PreferenceGroupDTO.OVERVIEW) {
            dto.setId(obj.getId());
            dto.setName(obj.getName());
            dto.setValue(obj.getValue());
        } else {
            dto.setId(obj.getId());
            dto.setTimestampC(obj.getTimestampC());
            dto.setTimestampU(obj.getTimestampU());
            dto.setCreatedBy(obj.getCreatedBy());
            dto.setUpdatedBy(obj.getUpdatedBy());
            dto.setName(obj.getName());
            dto.setValue(obj.getValue());
        }
        return dto;
    }
}
