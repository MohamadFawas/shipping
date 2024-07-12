package com.imara.shipping.dto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.Customer;
import com.imara.shipping.model.CustomerComplain;
import com.imara.shipping.repository.CustomerRepository;
import com.imara.shipping.service.CustomerComplainService;
import com.imara.shipping.service.CustomerService;
import com.imara.shipping.utility.TimeZoneConverter;
@Component
public class CustomerComplainDTOMapper extends AbstractDTOMapper<CustomerComplain, CustomerComplainDTO>{
	@Autowired
	private TimeZoneConverter tzConverter;
	@Autowired
	private CustomerService customerService; 

	@Override
	public CustomerComplain getEntity(CustomerComplainDTO dto) {
		if (dto == null) return null;
		CustomerComplain obj = new CustomerComplain();
		obj.setId(dto.getId());
	    obj.setTimestampC(tzConverter.convertFromUTC(dto.getTimestampC()));
	    obj.setTimestampU(tzConverter.convertFromUTC(dto.getTimestampU()));
	    obj.setCreatedBy(dto.getCreatedBy());
	    obj.setUpdatedBy(dto.getUpdatedBy());
	    obj.setComplain(dto.getComplain());
	    obj.setCustomer(customerService.findById(dto.getCustomerId()));
		return obj;	
	}

	@Override
	public CustomerComplainDTO getDTO(CustomerComplain obj, int format) {
		if (obj == null) {
            return null;
        }
		CustomerComplainDTO dto = new CustomerComplainDTO();
		dto.setId(obj.getId());
        dto.setCreatedBy(obj.getCreatedBy());
        dto.setUpdatedBy(obj.getUpdatedBy());
        dto.setTimestampC(obj.getTimestampC());
        dto.setTimestampU(obj.getTimestampU());
        dto.setComplain(obj.getComplain());
        dto.setCustomerId(obj.getCustomer().getId());
		return dto;
	}
	
}
