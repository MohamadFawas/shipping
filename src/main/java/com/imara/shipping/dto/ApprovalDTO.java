package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ApprovalDTO extends AbstractDTO {
    private LocalDate date;
    private String note;
    private long shipmentId;
    private boolean approved;
    private List<ApprovalImageDTO> images = new ArrayList<>();
}
