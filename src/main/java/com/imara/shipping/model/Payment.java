package com.imara.shipping.model;

import com.imara.shipping.model.core.AbstractObject;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "[payments]")
@Data
@SequenceGenerator(name = "payment_seq", allocationSize = 1)
public class Payment extends AbstractObject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
    private long id;
    private String name;
    private long paymentTypeId;
    private double price;
    private LocalDateTime date;
    private long shipmentId;
    private long clientId;
    private long driverId;
}
