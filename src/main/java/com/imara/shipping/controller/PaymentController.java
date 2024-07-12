package com.imara.shipping.controller;

import com.imara.shipping.controller.core.AbstractController;
import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.PaymentDTO;
import com.imara.shipping.dto.PaymentDTOMapper;
import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.Payment;
import com.imara.shipping.service.PaymentService;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/payment/")
@Slf4j
@Transactional
public class PaymentController extends AbstractController<Payment, PaymentDTO> {
    @Autowired
    PaymentService paymentService;
    @Autowired
    PaymentDTOMapper paymentDTOMapper;

    @Override
    protected AbstractService getService() {
        return paymentService;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    protected AbstractDTOMapper getDTOMapper() {
        return paymentDTOMapper;
    }

    @PostMapping("save")
    public Result<PaymentDTO> save(@RequestBody PaymentDTO paymentDTO) {
        return super.save(paymentDTO);
    }

    @GetMapping("get_all")
    public Result<List<PaymentDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping("get_by_id")
    public Result<PaymentDTO> getByID(@RequestParam("id") long id) {
        return super.getByID(id);
    }

    @PostMapping("delete")
    public Result deleteById(@RequestParam("id") long id) {
        return super.deleteById(id);
    }
}
