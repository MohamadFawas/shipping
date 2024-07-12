package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTO;
import lombok.Data;

@Data
public class ApprovalImageDTO extends AbstractDTO {
    private String image;
    private long approvalId;
}
