package com.imara.shipping.controller;

import com.imara.shipping.controller.core.AbstractController;
import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.DriverDTO;
import com.imara.shipping.dto.DriverDTOMapper;
import com.imara.shipping.model.Driver;
import com.imara.shipping.service.DriverService;
import com.imara.shipping.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver/")
@Slf4j
@Transactional
public class DriverController extends AbstractController<Driver, DriverDTO> {
    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverDTOMapper driverDTOMapper;
    @Autowired
    private RegistrationService registrationService;

    @Override
    protected DriverService getService() {
        return driverService;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    @Override
    protected DriverDTOMapper getDTOMapper() {
        return driverDTOMapper;
    }

    @PostMapping("save")
    public Result<DriverDTO> save(@RequestBody DriverDTO driverDTO) {
        return super.save(driverDTO);
    }

    @GetMapping("get_all")
    public Result<List<DriverDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping("get_by_id")
    public Result<DriverDTO> getByID(@RequestParam("id") long id) {
        return super.getByID(id);
    }

    @PostMapping("delete")
    public Result deleteById(@RequestParam("id") long id) {
        return super.deleteById(id);
    }

    @GetMapping("get_all_by_city_and_vehicle")
    public Result<List<DriverDTO>> getByCityAndVehicle(@RequestParam("cityId") long cityId, @RequestParam("vehicleTypeId") long vehicleTypeId) {
        return new Result(driverService.findAllByCityIdAndVehicleId(cityId, vehicleTypeId));
    }

    @PutMapping("update")
    public Result<DriverDTO> update(@RequestBody DriverDTO driverDTO) {
        return new Result<>(registrationService.updateDriver(driverDTO));
    }
}
