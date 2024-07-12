package com.imara.shipping.model;

import com.imara.shipping.model.core.AbstractObject;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "[user_permissions]")
@SequenceGenerator(name = "user_permission_seq", allocationSize = 1)
public class UserPermission extends AbstractObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_permission_seq")
    private long id;
    private long userId;
    private long systemPermissionId;
    private String permissions;     // JSON field
}
