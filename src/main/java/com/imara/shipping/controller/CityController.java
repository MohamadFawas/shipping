package com.imara.shipping.controller;

import com.imara.shipping.controller.core.AbstractController;
import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.CityDTO;
import com.imara.shipping.dto.CityDTOMapper;
import com.imara.shipping.model.City;
import com.imara.shipping.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city/")
@Slf4j
@Transactional
public class CityController extends AbstractController<City, CityDTO> {
    @Autowired
    CityService cityService;
    @Autowired
    CityDTOMapper cityDTOMapper;
    @Override
    protected CityService getService() {
        return cityService;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    protected CityDTOMapper getDTOMapper() {
        return cityDTOMapper;
    }

    @PostMapping("save")
    public Result<CityDTO> save(@RequestBody CityDTO cityDTO) {
        return super.save(cityDTO);
    }

    @GetMapping("get_all")
    public Result<List<CityDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping("get_by_id")
    public Result<CityDTO> getByID(@RequestParam("id") long id) {
        return super.getByID(id);
    }

    @PostMapping("delete")
    public Result deleteById(@RequestParam("id") long id) {
        return super.deleteById(id);
    }
}
