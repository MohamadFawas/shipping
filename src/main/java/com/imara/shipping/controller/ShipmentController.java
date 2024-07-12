package com.imara.shipping.controller;

import com.imara.shipping.controller.core.AbstractController;
import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.CalculateDTO;
import com.imara.shipping.dto.ShipmentDTO;
import com.imara.shipping.dto.ShipmentDTOMapper;
import com.imara.shipping.model.Shipment;
import com.imara.shipping.model.ShipmentStatus;
import com.imara.shipping.service.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipment/")
@Slf4j
@Transactional
public class ShipmentController extends AbstractController<Shipment, ShipmentDTO> {
    @Autowired
    private ShipmentService shipmentService;
    @Autowired
    private ShipmentDTOMapper shipmentDTOMapper;

    @Override
    protected ShipmentService getService() {
        return shipmentService;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    @Override
    protected ShipmentDTOMapper getDTOMapper() {
        return shipmentDTOMapper;
    }

    @PostMapping("save")
    public Result<ShipmentDTO> save(@RequestBody ShipmentDTO shipmentDTO) {
        return new Result<>(shipmentDTOMapper.getDTO(shipmentService.save(shipmentDTOMapper.getEntity(shipmentDTO))));
    }

    @GetMapping("get_all")
    public Result<List<ShipmentDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping("get_by_id")
    public Result<ShipmentDTO> getByID(@RequestParam("id") long id) {
        return super.getByID(id);
    }

    @PostMapping("delete")
    public Result deleteById(@RequestParam("id") long id) {
        return super.deleteById(id);
    }

    @GetMapping("get_all_by_customer")
    public Result<List<ShipmentDTO>> getAllByCustomerId(@RequestParam("id") long id) {
        return new Result(shipmentDTOMapper.getDTOList(shipmentService.findByCustomerId(id)));
    }

    @GetMapping("get_all_by_driver")
    public Result<List<ShipmentDTO>> getAllByDriverId(@RequestParam("id") long id) {
        return new Result(shipmentDTOMapper.getDTOList(shipmentService.findByDriverId(id)));
    }

    @GetMapping("get_all_by_status")
    public Result<List<ShipmentDTO>> getAllByStatus(@RequestParam("status") ShipmentStatus status) {
        return new Result(shipmentDTOMapper.getDTOList(shipmentService.findByStatus(status)));
    }


    @GetMapping("get_all_by_driver_and_status")
    public Result<List<ShipmentDTO>> getAllByDriverId(@RequestParam("id") long id, @RequestParam("status") ShipmentStatus status) {
        return new Result(shipmentDTOMapper.getDTOList(shipmentService.findByDriverIdAndStatus(id, status)));
    }

    @GetMapping("get_all_by_customer_and_status")
    public Result<List<ShipmentDTO>> getAllByCustomerId(@RequestParam("id") long id, @RequestParam("status") ShipmentStatus status) {
        return new Result(shipmentDTOMapper.getDTOList(shipmentService.findByCustomerIdAndStatus(id, status)));
    }


    @PutMapping("update_status")
    public Result<Object> updateStatus(@RequestParam("id") long id, @RequestParam("status") ShipmentStatus status) {
        shipmentService.updateStatusById(id, status);
        return new Result<>();
    }

    @PostMapping("calculate_cost")
    public Result<CalculateDTO> calculateCost(@RequestBody CalculateDTO calculateDTO) {
        return new Result<>(shipmentService.calculatePrice(calculateDTO));
    }

    @PutMapping("auto_select")
    public Result<ShipmentDTO> autoSelect(@RequestParam("id") long id, @RequestParam("driverId") long driverId) {
        shipmentService.autoSelect(id, driverId);
        return new Result<>();
    }

    @GetMapping("get_all_by_city")
    public Result<List<ShipmentDTO>> getAllByCity(@RequestParam("cityId") long cityId) {
        return new Result<>(shipmentDTOMapper.getDTOList(shipmentService.getAllByCity(cityId)));
    }


}
