package com.imara.shipping.model;

import com.imara.shipping.model.core.AbstractObject;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "[status_updated_time]")
public class StatusUpdatedTime extends AbstractObject {


    @Id
    @SequenceGenerator(name = "status_updated_time_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_updated_time_seq")
    private long id;
    private long shipmentId;
    private LocalDateTime waitingAcceptance;
    private LocalDateTime driverAccepted;
    private LocalDateTime waitingPickupConfirmation;
    private LocalDateTime pickUpConfirmed;
    private LocalDateTime shipmentStarted;
    private LocalDateTime shipmentCompleted;

}
