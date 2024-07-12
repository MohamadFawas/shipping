package com.imara.shipping.repository;

import com.imara.shipping.model.Customer;
import com.imara.shipping.repository.core.AbstractRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends AbstractRepository<Customer, Long> {
    List<Customer> findAllByCityId(long cityId);
}
