package com.imara.shipping.controller;

import com.imara.shipping.controller.core.AbstractController;
import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.ShipmentItemDTO;
import com.imara.shipping.dto.ShipmentItemDTOMapper;
import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.ShipmentItem;
import com.imara.shipping.service.ShipmentItemService;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/shipment_item")
@Slf4j
@Transactional
public class ShipmentItemController extends AbstractController<ShipmentItem, ShipmentItemDTO> {
    @Autowired
    private ShipmentItemDTOMapper shipmentItemDTOMapper;
    @Autowired
    private ShipmentItemService shipmentItemService;

    @Override
    protected AbstractService getService() {
        return shipmentItemService;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    @Override
    protected AbstractDTOMapper getDTOMapper() {
        return shipmentItemDTOMapper;
    }

    @PostMapping("save")
    public Result<ShipmentItemDTO> save(@RequestBody ShipmentItemDTO shipmentItemDTO) {
        return super.save(shipmentItemDTO);
    }

    @GetMapping("get_all")
    public Result<List<ShipmentItemDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping("get_by_id")
    public Result<ShipmentItemDTO> getByID(@RequestParam("id") long id) {
        return super.getByID(id);
    }

    @PostMapping("delete")
    public Result deleteById(@RequestParam("id") long id) {
        return super.deleteById(id);
    }


}
