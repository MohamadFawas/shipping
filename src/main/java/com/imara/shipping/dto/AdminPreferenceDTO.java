package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTO;
import lombok.Data;

@Data
public class AdminPreferenceDTO extends AbstractDTO {
    private String name;
    private String value;
    private long userId;
}
