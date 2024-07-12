package com.imara.shipping.controller;

import com.imara.shipping.controller.core.AbstractController;
import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.DriverDTO;
import com.imara.shipping.dto.ShipmentBidDTO;
import com.imara.shipping.dto.ShipmentBidDTOMapper;
import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.ShipmentBid;
import com.imara.shipping.model.ShipmentBidStatus;
import com.imara.shipping.service.ShipmentBidService;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipment_bid/")
@Slf4j
@Transactional
public class ShipmentBidController extends AbstractController<ShipmentBid, ShipmentBidDTO> {
    @Autowired
    private ShipmentBidService shipmentBidService;
    @Autowired
    private ShipmentBidDTOMapper shipmentBidDTOMapper;

    @Override
    protected AbstractDTOMapper getDTOMapper() {
        return shipmentBidDTOMapper;
    }

    @Override
    protected AbstractService getService() {
        return shipmentBidService;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    @PostMapping("save")
    public Result<ShipmentBidDTO> save(@RequestBody ShipmentBidDTO shipmentBidDTO) {
        shipmentBidDTO.setStatus(ShipmentBidStatus.PENDING);
        return super.save(shipmentBidDTO);
    }

    @GetMapping("get_all")
    public Result<List<ShipmentBidDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping("get_by_id")
    public Result<ShipmentBidDTO> getByID(@RequestParam("id") long id) {
        return super.getByID(id);
    }

    @DeleteMapping("delete")
    public Result deleteById(@RequestParam("id") long id) {
        return super.deleteById(id);
    }

    @GetMapping("get_all_by_shipment_and_status")
    public Result<List<ShipmentBidDTO>> getAllByShipmentIdAndStatus(@RequestParam("shipmentId") long shipmentId, @RequestParam("status") ShipmentBidStatus status) {
        return new Result(shipmentBidDTOMapper.getDTOList(shipmentBidService.getAllByShipmentIdAndStatus(shipmentId, status), ShipmentBidDTO.DEFAULT));
    }

    @GetMapping("get_all_by_driver_and_status")
    public Result<List<ShipmentBidDTO>> getAllByAndDriverIdAndStatus(@RequestParam("driverId") long driverId, @RequestParam("status") ShipmentBidStatus status) {
        return new Result(shipmentBidDTOMapper.getDTOList(shipmentBidService.getAllByDriverIdAndStatus(driverId, status), DriverDTO.DEFAULT));
    }

    @GetMapping("get_all_by_shipment")
    public Result<List<ShipmentBidDTO>> getAllByAndShipmentId(@RequestParam("shipmentId") long shipmentId) {
        return new Result(shipmentBidDTOMapper.getDTOList(shipmentBidService.getAllByShipmentId(shipmentId), DriverDTO.DEFAULT));
    }

    @GetMapping("get_all_by_driver")
    public Result<List<ShipmentBidDTO>> getAllByAndDriverId(@RequestParam("driverId") long driverId) {
        return new Result(shipmentBidDTOMapper.getDTOList(shipmentBidService.getAllByDriverId(driverId), DriverDTO.DEFAULT));
    }

    @PutMapping("accept_bid")
    public Result<List<ShipmentBidDTO>> acceptBid(@RequestParam("shipmentId") long shipmentId, @RequestParam("bidId") long bidId) {
        shipmentBidService.acceptBid(shipmentId, bidId);
        return new Result<>();
    }
}
