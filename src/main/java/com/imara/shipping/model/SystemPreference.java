package com.imara.shipping.model;

import com.imara.shipping.model.core.AbstractObject;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "[system_preferences]")
@SequenceGenerator(name = "system_preference_seq", allocationSize = 1)
public class SystemPreference extends AbstractObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_preference_seq")
    private long id;

    @NotNull
    private long preferenceGroupId;

    @Column(unique = true)
    @NotNull
    private String name;

    private String value;
}
