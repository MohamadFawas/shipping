package com.imara.shipping.service;

import com.imara.shipping.model.City;
import com.imara.shipping.repository.CityRepository;
import com.imara.shipping.repository.core.AbstractRepository;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class CityService extends AbstractService<City> {
    @Autowired
    CityRepository cityRepository;

    @Override
    protected AbstractRepository getRepository() {
        return cityRepository;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    public void saveCities(List<City> cityList) {
        Iterator<City> cities = cityList.iterator();
        while (cities.hasNext()) {
            City newCity = cities.next();
            City city = findById(newCity.getId());
            if (city == null) {
                save(newCity);
            }
        }
    }
}
