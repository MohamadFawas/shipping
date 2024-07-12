package com.imara.shipping.model;

import com.imara.shipping.model.core.AbstractObject;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "[cities]")
@SequenceGenerator(name = "city_seq", allocationSize = 1)
public class City extends AbstractObject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_seq")
    private long id;
    private String englishName;
    private String arabicName;
    private String postcode;
    private long latitude;
    private long longitude;
}
