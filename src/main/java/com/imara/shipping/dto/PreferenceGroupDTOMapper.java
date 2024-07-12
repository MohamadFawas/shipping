package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.PreferenceGroup;
import com.imara.shipping.utility.TimeZoneConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PreferenceGroupDTOMapper extends AbstractDTOMapper<PreferenceGroup, PreferenceGroupDTO> {

    @Autowired
    private TimeZoneConverter tzConverter;

    @Override
    public PreferenceGroup getEntity(PreferenceGroupDTO dto) {
        if (dto == null) return null;
        PreferenceGroup obj = new PreferenceGroup();
        obj.setId(dto.getId());
        obj.setTimestampC(tzConverter.convertFromUTC(dto.getTimestampC()));
        obj.setTimestampU(tzConverter.convertFromUTC(dto.getTimestampU()));
        obj.setCreatedBy(dto.getCreatedBy());
        obj.setUpdatedBy(dto.getUpdatedBy());
        obj.setName(dto.getName());
        obj.setParentId(dto.getParentId());
        return obj;
    }

    @Override
    public PreferenceGroupDTO getDTO(PreferenceGroup obj, int format) {
        if (obj == null) return null;
        //
        PreferenceGroupDTO dto = new PreferenceGroupDTO();
        if(format == PreferenceGroupDTO.OVERVIEW)
        {
            dto.setId(obj.getId());
            dto.setName(obj.getName());
            dto.setParentId(obj.getParentId());
        }
        else
        {
            dto.setId(obj.getId());
            dto.setTimestampC(obj.getTimestampC());
            dto.setTimestampU(obj.getTimestampU());
            dto.setCreatedBy(obj.getCreatedBy());
            dto.setUpdatedBy(obj.getUpdatedBy());
            dto.setName(obj.getName());
            dto.setParentId(obj.getParentId());
        }
        return dto;
    }
}
