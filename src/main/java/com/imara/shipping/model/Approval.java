package com.imara.shipping.model;

import com.imara.shipping.model.core.AbstractObject;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "[approvals]")
@SequenceGenerator(name = "approval_seq", allocationSize = 1)
public class Approval extends AbstractObject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "approval_seq")
    private long id;
    private LocalDate date;
    @Column(columnDefinition = "text")
    private String note;
    private long shipmentId;
    private boolean approved;
    @Transient
    private List<ApprovalImage> images = new ArrayList<>();
}
