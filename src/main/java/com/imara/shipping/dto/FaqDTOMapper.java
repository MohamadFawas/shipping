package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.City;
import com.imara.shipping.model.Faq;
import com.imara.shipping.utility.TimeZoneConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FaqDTOMapper extends AbstractDTOMapper<Faq,FaqDTO> {

    @Autowired
    private TimeZoneConverter tzConverter;
    @Override
    public Faq getEntity(FaqDTO dto) {
        if (dto == null) return null;
        Faq obj = new Faq();
        obj.setId(dto.getId());
        obj.setTimestampC(tzConverter.convertFromUTC(dto.getTimestampC()));
        obj.setTimestampU(tzConverter.convertFromUTC(dto.getTimestampU()));
        obj.setCreatedBy(dto.getCreatedBy());
        obj.setUpdatedBy(dto.getUpdatedBy());
        obj.setQuestionEn(dto.getQuestionEn());
        obj.setQuestionAr(dto.getQuestionAr());
        obj.setAnswerEn(dto.getAnswerEn());
        obj.setAnswerAr(dto.getAnswerAr());
        return obj;
    }
    @Override
    public FaqDTO getDTO(Faq obj, int format) {
        if (obj == null) return null;
        FaqDTO dto = new FaqDTO();
        dto.setId(obj.getId());
        dto.setTimestampC(obj.getTimestampC());
        dto.setTimestampU(obj.getTimestampU());
        dto.setCreatedBy(obj.getCreatedBy());
        dto.setUpdatedBy(obj.getUpdatedBy());
        dto.setQuestionEn(obj.getQuestionEn());
        dto.setQuestionAr(obj.getQuestionAr());
        dto.setAnswerEn(obj.getAnswerEn());
        dto.setAnswerAr(obj.getAnswerAr());
        return dto;
    }
}
