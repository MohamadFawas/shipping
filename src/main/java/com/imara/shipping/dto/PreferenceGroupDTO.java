package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTO;
import lombok.Data;

@Data
public class PreferenceGroupDTO extends AbstractDTO {
    private long parentId;
    private String name;
}
