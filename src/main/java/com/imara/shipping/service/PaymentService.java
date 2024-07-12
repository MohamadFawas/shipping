package com.imara.shipping.service;

import com.imara.shipping.model.Payment;
import com.imara.shipping.repository.PaymentRepository;
import com.imara.shipping.repository.core.AbstractRepository;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService extends AbstractService<Payment> {
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    protected AbstractRepository getRepository() {
        return paymentRepository;
    }

    @Override
    protected Logger getLog() {
        return log;
    }
}
