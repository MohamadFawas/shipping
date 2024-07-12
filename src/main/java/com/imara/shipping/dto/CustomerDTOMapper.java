package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.Customer;
import com.imara.shipping.service.CityService;
import com.imara.shipping.utility.TimeZoneConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDTOMapper extends AbstractDTOMapper<Customer, CustomerDTO> {
    @Autowired
    private CityDTOMapper cityDTOMapper;
    @Autowired
    private CityService cityService;
    @Autowired
    private TimeZoneConverter tzConverter;

    @Override
    public Customer getEntity(CustomerDTO dto) {
        if (dto == null) {
            return null;
        }
        Customer obj = new Customer();
        obj.setId(dto.getId());
        obj.setFirstName(dto.getFirstName());
        obj.setLastName(dto.getLastName());
        obj.setCityId(dto.getCityId());
        obj.setEmail(dto.getEmail());
        obj.setUpdatedBy(dto.getUpdatedBy());
        obj.setCreatedBy(dto.getCreatedBy());
        obj.setTimestampC(tzConverter.convertFromUTC(dto.getTimestampC()));
        obj.setTimestampU(tzConverter.convertFromUTC(dto.getTimestampU()));
        obj.setPasswordN(dto.getPasswordN());
        obj.setFullName(dto.getFullName());
        obj.setPhoneNumber(dto.getPhoneNumber());
        obj.setAddress(dto.getAddress());
        return obj;
    }

    @Override
    public CustomerDTO getDTO(Customer obj, int format) {
        if (obj == null) {
            return null;
        }
        CustomerDTO dto = new CustomerDTO();
        dto.setId(obj.getId());
        dto.setCreatedBy(obj.getCreatedBy());
        dto.setUpdatedBy(obj.getUpdatedBy());
        dto.setTimestampC(obj.getTimestampC());
        dto.setTimestampU(obj.getTimestampU());
        dto.setEmail(obj.getEmail());
        dto.setFirstName(obj.getFirstName());
        dto.setLastName(obj.getLastName());
        dto.setCityId(obj.getCityId());
        dto.setPhoneNumber(obj.getPhoneNumber());
        dto.setFullName(obj.getFullName());
        dto.setAddress(obj.getAddress());
        dto.setCity(cityDTOMapper.getDTO(cityService.findById(obj.getCityId())));
        return dto;
    }
}
