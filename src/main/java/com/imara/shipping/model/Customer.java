package com.imara.shipping.model;

import com.imara.shipping.model.core.AbstractObject;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "[customers]")
@SequenceGenerator(name = "customer_seq", allocationSize = 1)
public class Customer extends AbstractObject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private long cityId;
    private String phoneNumber;
    private String fullName;
    @Transient
    private String passwordN;
    @Column(columnDefinition = "text")
    private String address;


}
