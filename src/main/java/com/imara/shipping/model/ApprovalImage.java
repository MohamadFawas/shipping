package com.imara.shipping.model;

import com.imara.shipping.model.core.AbstractObject;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "[approval_images]")
@SequenceGenerator(name = "approval_images_seq",allocationSize = 1)
public class ApprovalImage extends AbstractObject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "approval_images_seq")
    private long id;
    private String image;
    private long approvalId;
}
