package com.imara.shipping.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.imara.shipping.model.core.AbstractObject;

import lombok.Data;

@Data
@Entity
@Table(name = "[customers_complain]")
@SequenceGenerator(name = "customer_complain_seq", allocationSize = 1)
public class CustomerComplain extends AbstractObject{
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_complain_seq")
    private long id;
	private String complain;
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
}
