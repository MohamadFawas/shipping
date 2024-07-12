package com.imara.shipping.controller;

import com.imara.shipping.controller.core.AbstractController;
import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.PaymentTypeDTO;
import com.imara.shipping.dto.PaymentTypeDTOMapper;
import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.PaymentType;
import com.imara.shipping.service.PaymentTypeService;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/payment_type/")
@Slf4j
@Transactional
public class PaymentTypeController extends AbstractController<PaymentType, PaymentTypeDTO> {
    @Autowired
    private PaymentTypeService paymentTypeService;
    @Autowired
    private PaymentTypeDTOMapper paymentTypeDTOMapper;

    @Override
    protected AbstractService getService() {
        return paymentTypeService;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    @Override
    protected AbstractDTOMapper getDTOMapper() {
        return paymentTypeDTOMapper;
    }

    @PostMapping("save")
    public Result<PaymentTypeDTO> save(@RequestBody PaymentTypeDTO shipmentDTO) {
        return super.save(shipmentDTO);
    }

    @GetMapping("get_all")
    public Result<List<PaymentTypeDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping("get_by_id")
    public Result<PaymentTypeDTO> getByID(@RequestParam("id") long id) {
        return super.getByID(id);
    }

    @PostMapping("delete")
    public Result deleteById(@RequestParam("id") long id) {
        return super.deleteById(id);
    }
}
