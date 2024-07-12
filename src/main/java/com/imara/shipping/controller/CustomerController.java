package com.imara.shipping.controller;

import com.imara.shipping.controller.core.AbstractController;
import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.CustomerDTO;
import com.imara.shipping.dto.CustomerDTOMapper;
import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.Customer;
import com.imara.shipping.service.CustomerService;
import com.imara.shipping.service.RegistrationService;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/customer/")
@Slf4j
@Transactional
public class CustomerController extends AbstractController<Customer, CustomerDTO> {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerDTOMapper customerDTOMapper;
    @Autowired
    protected RegistrationService registrationService;

    @Override
    protected AbstractService getService() {
        return customerService;
    }


    @Override
    protected Logger getLog() {
        return log;
    }

    @Override
    protected AbstractDTOMapper getDTOMapper() {
        return customerDTOMapper;
    }

    @PostMapping("save")
    public Result<CustomerDTO> save(@RequestBody CustomerDTO customerDTO) {
        return super.save(customerDTO);
    }

    @GetMapping("get_all")
    public Result<List<CustomerDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping("get_by_id")
    public Result<CustomerDTO> getByID(@RequestParam("id") long id) {
        return super.getByID(id);
    }

    @PostMapping("delete")
    public Result deleteById(@RequestParam("id") long id) {
        return super.deleteById(id);
    }

    @PutMapping("update")
    public Result<CustomerDTO> update(@RequestBody CustomerDTO customerDTO) {
        return new Result<>(registrationService.updateCustomer(customerDTO));
    }
}
