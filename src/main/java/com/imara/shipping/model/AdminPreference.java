package com.imara.shipping.model;

import com.imara.shipping.model.core.AbstractObject;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "[admin_preferences]")
@SequenceGenerator(name = "admin_preference_seq", allocationSize = 1)
public class AdminPreference extends AbstractObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_preference_seq")
    private long id;
    @NotNull
    private String name;    // this name should be in 'system_preference'

    private String value;
}
