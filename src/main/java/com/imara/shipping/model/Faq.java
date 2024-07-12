package com.imara.shipping.model;

import com.imara.shipping.model.core.AbstractObject;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "[faq]")
public class Faq extends AbstractObject {

    @Id
    @SequenceGenerator(name = "faq_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "faq_seq")
    private long id;
    private String questionEn;
    private String questionAr;

    @Column(columnDefinition = "text")
    private String answerEn;

    @Column(columnDefinition = "text")
    private String answerAr;

}
