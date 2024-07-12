package com.imara.shipping.service;

import com.imara.shipping.model.Customer;
import com.imara.shipping.model.CustomerComplain;
import com.imara.shipping.repository.CustomerRepository;
import com.imara.shipping.repository.core.AbstractRepository;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService extends AbstractService<Customer> {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    protected AbstractRepository getRepository() {
        return customerRepository;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    public List<Customer> findAllByCityId(long cityId) {
        return customerRepository.findAllByCityId(cityId);
    }
    
}
