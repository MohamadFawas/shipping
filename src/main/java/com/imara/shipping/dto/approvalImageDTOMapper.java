package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.ApprovalImage;
import com.imara.shipping.utility.TimeZoneConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class approvalImageDTOMapper extends AbstractDTOMapper<ApprovalImage, ApprovalImageDTO> {
    @Autowired
    private TimeZoneConverter tzConverter;

    @Override
    public ApprovalImage getEntity(ApprovalImageDTO dto) {
        if (dto == null) {
            return null;
        }
        ApprovalImage obj = new ApprovalImage();
        obj.setId(dto.getId());
        obj.setImage(dto.getImage());
        obj.setApprovalId(dto.getApprovalId());
        obj.setCreatedBy(dto.getCreatedBy());
        obj.setUpdatedBy(dto.getUpdatedBy());
        obj.setTimestampC(tzConverter.convertFromUTC(dto.getTimestampC()));
        obj.setTimestampU(tzConverter.convertFromUTC(dto.getTimestampU()));
        return obj;
    }

    @Override
    public ApprovalImageDTO getDTO(ApprovalImage obj, int format) {
        ApprovalImageDTO dto = new ApprovalImageDTO();
        if (obj == null) {
            return null;
        }
        dto.setId(obj.getId());
        dto.setImage(obj.getImage());
        dto.setCreatedBy(obj.getCreatedBy());
        dto.setUpdatedBy(obj.getUpdatedBy());
        dto.setTimestampU(obj.getTimestampU());
        dto.setTimestampC(obj.getTimestampC());
        dto.setApprovalId(obj.getApprovalId());
        return dto;
    }
}
