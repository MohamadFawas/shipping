package com.imara.shipping.model;

import com.imara.shipping.model.core.AbstractObject;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "[drivers]")
@SequenceGenerator(name = "driver_seq", allocationSize = 1)
@AttributeOverride(name = "username", column = @Column(name = "driver_username"))
public class Driver extends AbstractObject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_seq")
    private long id;
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
    private double rating;
    private String phoneNumber;
    private String nationality;
    private boolean isPrivate;
    private long cityId;
    @Transient
    private String passwordN;
    private String fullName;
    private long totalTrips;
    private double experience;
    private String companyName;
    @Column(columnDefinition = "text")
    private String companyAddress;
    private String registrationNumber;
    private String companyPhoneNumber;

}
