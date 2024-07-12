package com.imara.shipping.model;

import com.imara.shipping.model.core.AbstractObject;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "[preference_groups]")
@SequenceGenerator(name = "preference_group_seq", allocationSize = 1)
public class PreferenceGroup extends AbstractObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preference_group_seq")
    private long id;

    @NotNull
    private long parentId;

    @Column(unique = true)
    @NotNull
    private String name;
}
