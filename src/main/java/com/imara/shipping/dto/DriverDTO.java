package com.imara.shipping.dto;

import com.imara.shipping.dto.core.AbstractDTO;
import lombok.Data;

import java.time.LocalDate;
@Data
public class DriverDTO extends AbstractDTO {
    private long id;
    private String fullName;
    private String firstName;
    private String lastName;
    private long licenseNumber;
    private LocalDate licenseExpieryDate;
    private LocalDate dob;
    private boolean isActive;
    private double latitude;
    private double longitude;
    private String licenseClass;
    private String profilePicture;
    private long iqamaId;
    private LocalDate iqamaExpieryDate;
    private String nationality;
    private double rating;
    private boolean isPrivate;
    private long cityId;
    private String phoneNumber;
    private String passwordN;
    private long totalTrips;
    private double experience;
    private String companyName;
    private String companyAddress;
    private String registrationNumber;
    private String companyPhoneNumber;
    private CityDTO city;
}
