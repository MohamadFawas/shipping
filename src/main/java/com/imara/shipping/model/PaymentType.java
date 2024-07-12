package com.imara.shipping.model;

import com.imara.shipping.model.core.AbstractObject;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
@SequenceGenerator(name = "payment_type_seq", allocationSize = 1)
public class PaymentType extends AbstractObject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_type_seq")
    private long id;
    private String name;
    private long cardNumber;
    private int cvc;
    private LocalDate expiryDate;

}
