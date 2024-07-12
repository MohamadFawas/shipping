package com.imara.shipping.repository;

import java.util.List;
import java.util.Optional;

import com.imara.shipping.model.CustomerComplain;
import com.imara.shipping.repository.core.AbstractRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerComplainRepository extends AbstractRepository<CustomerComplain,Long>{
	
	List<CustomerComplain> findAllByCustomerId(long customerId);
	//@Query("SELECT p FROM CustomerComplain p WHERE p.complain LIKE %:complain%")
    List<CustomerComplain> findByComplainContaining(@Param("complain") String complain);
}
