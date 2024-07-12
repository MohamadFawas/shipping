package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.Driver;
import com.imara.shipping.service.CityService;
import com.imara.shipping.utility.TimeZoneConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DriverDTOMapper extends AbstractDTOMapper<Driver, DriverDTO> {
    @Autowired
    private CityService cityService;
    @Autowired
    private TimeZoneConverter tzConverter;
    @Autowired
    private UserDTOMapper userDTOMapper;
    @Autowired
    private CityDTOMapper cityDTOMapper;

    @Override
    public Driver getEntity(DriverDTO dto) {
        if (dto == null) return null;
        Driver obj = new Driver();
        obj.setId(dto.getId());
        obj.setTimestampC(tzConverter.convertFromUTC(dto.getTimestampC()));
        obj.setTimestampU(tzConverter.convertFromUTC(dto.getTimestampU()));
        obj.setCreatedBy(dto.getCreatedBy());
        obj.setUpdatedBy(dto.getUpdatedBy());
        obj.setFirstName(dto.getFirstName());
        obj.setLastName(dto.getLastName());
        obj.setDob(dto.getDob());
        obj.setActive(dto.isActive());
        obj.setLicenseExpieryDate(dto.getLicenseExpieryDate());
        obj.setIqamaId(dto.getIqamaId());
        obj.setLatitude(dto.getLatitude());
        obj.setLongitude(dto.getLongitude());
        obj.setLicenseNumber(dto.getLicenseNumber());
        obj.setLicenseClass(dto.getLicenseClass());
        obj.setProfilePicture(dto.getProfilePicture());
        obj.setIqamaExpieryDate(dto.getIqamaExpieryDate());
        obj.setRating(dto.getRating());
        obj.setActive(dto.isActive());
        obj.setPrivate(dto.isPrivate());
        obj.setCityId(dto.getCityId());
        obj.setPasswordN(dto.getPasswordN());
        obj.setPhoneNumber(dto.getPhoneNumber());
        obj.setFullName(dto.getFullName());
        obj.setExperience(dto.getExperience());
        obj.setTotalTrips(dto.getTotalTrips());
        obj.setCompanyName(dto.getCompanyName());
        obj.setCompanyAddress(dto.getCompanyAddress());
        obj.setCompanyPhoneNumber(dto.getCompanyPhoneNumber());
        obj.setRegistrationNumber(dto.getRegistrationNumber());
        obj.setNationality(dto.getNationality());
        return obj;
    }
    @Override
    public DriverDTO getDTO(Driver obj, int format) {
        if (obj == null) return null;
        DriverDTO dto = new DriverDTO();
        dto.setId(obj.getId());
        dto.setTimestampC(obj.getTimestampC());
        dto.setTimestampU(obj.getTimestampU());
        dto.setCreatedBy(obj.getCreatedBy());
        dto.setUpdatedBy(obj.getUpdatedBy());
        dto.setFirstName(obj.getFirstName());
        dto.setLastName(obj.getLastName());
        dto.setDob(obj.getDob());
        dto.setLatitude(obj.getLatitude());
        dto.setLongitude(obj.getLongitude());
        dto.setActive(obj.isActive());
        dto.setLicenseExpieryDate(obj.getLicenseExpieryDate());
        dto.setIqamaId(obj.getIqamaId());
        dto.setLicenseNumber(obj.getLicenseNumber());
        dto.setLicenseClass(obj.getLicenseClass());
        dto.setProfilePicture(obj.getProfilePicture());
        dto.setIqamaExpieryDate(obj.getIqamaExpieryDate());
        dto.setRating(obj.getRating());
        dto.setPrivate(obj.isPrivate());
        dto.setCityId(obj.getCityId());
        dto.setPhoneNumber(obj.getPhoneNumber());
        dto.setPasswordN(obj.getPasswordN());
        dto.setFullName(obj.getFullName());
        dto.setExperience(obj.getExperience());
        dto.setCompanyName(obj.getCompanyName());
        dto.setCompanyAddress(obj.getCompanyAddress());
        dto.setCompanyPhoneNumber(obj.getCompanyPhoneNumber());
        dto.setRegistrationNumber(obj.getRegistrationNumber());
        dto.setNationality(obj.getNationality());
        dto.setCity(cityDTOMapper.getDTO(cityService.findById(obj.getCityId())));
        return dto;
    }
}
