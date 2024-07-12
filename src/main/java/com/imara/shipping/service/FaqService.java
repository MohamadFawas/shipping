package com.imara.shipping.service;


import com.imara.shipping.model.City;
import com.imara.shipping.model.Faq;
import com.imara.shipping.repository.CityRepository;
import com.imara.shipping.repository.FaqRepository;
import com.imara.shipping.repository.core.AbstractRepository;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FaqService extends AbstractService<Faq> {

    @Autowired
    FaqRepository faqRepository;
    @Override
    protected AbstractRepository getRepository() {
        return faqRepository;
    }

    @Override
    protected Logger getLog() {
        return log;
    }
}
