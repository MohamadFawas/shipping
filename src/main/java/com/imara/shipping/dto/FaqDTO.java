package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTO;
import lombok.Data;

import javax.persistence.Column;

@Data
public class FaqDTO extends AbstractDTO {

    private long id;
    private String questionEn;
    private String questionAr;
    private String answerEn;
    private String answerAr;
}
