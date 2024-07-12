package com.imara.shipping.dto;



import com.imara.shipping.dto.core.AbstractDTO;

import lombok.Data;

@Data
public class CustomerComplainDTO extends AbstractDTO {
	
	private long id;
	private String complain;
	private long customerId;
}
