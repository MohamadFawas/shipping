package com.imara.shipping.service;

import com.imara.shipping.model.Driver;
import com.imara.shipping.repository.DriverRepository;
import com.imara.shipping.repository.core.AbstractRepository;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DriverService extends AbstractService<Driver> {
    @Autowired
    DriverRepository driverRepository;

    @Override
    protected AbstractRepository getRepository() {
        return driverRepository;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    public List<Driver> findAllByCityIdAndVehicleId(long cityId, long vehicleTypeId) {
        return driverRepository.findDriversByCityIdAndVehicleTypeId(cityId, vehicleTypeId);
    }
}
