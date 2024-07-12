package com.imara.shipping.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imara.shipping.model.CustomerComplain;
import com.imara.shipping.repository.CustomerComplainRepository;
import com.imara.shipping.repository.core.AbstractRepository;
import com.imara.shipping.service.core.AbstractService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class CustomerComplainService extends AbstractService<CustomerComplain>{
	
	@Autowired
	private CustomerComplainRepository customerComplainRepository;
	
	@Override
	protected AbstractRepository getRepository() {
		// TODO Auto-generated method stub
		return customerComplainRepository;
	}

	@Override
	protected Logger getLog() {
		return log;
	}
	
	public List<CustomerComplain> findAllByCustomerId(long customerId){
		return customerComplainRepository.findAllByCustomerId(customerId);
	}
	
	public List<CustomerComplain> searchByComplain(String complain) {
        return customerComplainRepository.findByComplainContaining(complain);
    }
}
