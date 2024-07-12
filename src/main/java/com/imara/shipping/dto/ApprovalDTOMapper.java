package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.Approval;
import com.imara.shipping.model.ApprovalImage;
import com.imara.shipping.service.ApprovalImageService;
import com.imara.shipping.utility.TimeZoneConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class ApprovalDTOMapper extends AbstractDTOMapper<Approval, ApprovalDTO> {

    @Autowired
    private TimeZoneConverter tzConverter;
    @Autowired
    private approvalImageDTOMapper approvalimageDTOMapper;
    @Autowired
    private ApprovalImageService approvalImageService;

    @Override
    public Approval getEntity(ApprovalDTO dto) {
        Approval obj = new Approval();
        if (dto == null) {
            return null;
        }
        obj.setApproved(dto.isApproved());
        obj.setId(dto.getId());
        obj.setDate(dto.getDate());
        obj.setCreatedBy(dto.getCreatedBy());
        obj.setUpdatedBy(dto.getUpdatedBy());
        obj.setNote(dto.getNote());
        obj.setShipmentId(dto.getShipmentId());
        obj.setTimestampC(tzConverter.convertFromUTC(dto.getTimestampC()));
        obj.setTimestampU(tzConverter.convertFromUTC(dto.getTimestampU()));

        for (ApprovalImageDTO imageDTO : dto.getImages()) {
            ApprovalImage image = approvalimageDTOMapper.getEntity(imageDTO);
            obj.getImages().add(image);
        }
        return obj;
    }

    @Override
    public ApprovalDTO getDTO(Approval obj, int format) {
        ApprovalDTO dto = new ApprovalDTO();
        if (obj == null) {
            return null;
        }
        dto.setId(obj.getId());
        dto.setNote(obj.getNote());
        dto.setApproved(obj.isApproved());
        dto.setDate(obj.getDate());
        dto.setUpdatedBy(obj.getUpdatedBy());
        dto.setCreatedBy(obj.getCreatedBy());
        dto.setShipmentId(obj.getShipmentId());
        dto.setTimestampC(obj.getTimestampC());
        dto.setTimestampU(obj.getTimestampU());

        for (ApprovalImage approvalImage : approvalImageService.getImagesById(obj.getId())) {
            ApprovalImageDTO imageDTO = approvalimageDTOMapper.getDTO(approvalImage);
            dto.getImages().add(imageDTO);
        }
        return dto;
    }
}
