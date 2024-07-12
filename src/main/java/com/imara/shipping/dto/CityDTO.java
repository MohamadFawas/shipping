package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTO;
import lombok.Data;


@Data
public class CityDTO extends AbstractDTO {
    private String englishName;
    private String arabicName;
    private String postCode;
    private long latitude;
    private long longitude;
}
