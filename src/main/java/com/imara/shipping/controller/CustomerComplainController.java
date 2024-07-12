package com.imara.shipping.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imara.shipping.controller.core.AbstractController;
import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.CustomerComplainDTO;
import com.imara.shipping.dto.CustomerComplainDTOMapper;
import com.imara.shipping.model.Customer;
import com.imara.shipping.model.CustomerComplain;
import com.imara.shipping.service.CustomerComplainService;
import com.imara.shipping.service.core.AbstractService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customerComplain/")
@Slf4j
@Transactional
public class CustomerComplainController  extends AbstractController<CustomerComplain,CustomerComplainDTO>{
	@Autowired
	private CustomerComplainService customerComplainService;
	@Autowired
	private CustomerComplainDTOMapper customerComplainDTOMapper;
	
	@Override
	protected AbstractService getService() {
		return customerComplainService;
	}

	@Override
	protected Logger getLog() {
		return log;
	}
	
	protected CustomerComplainDTOMapper getDTOMapper() {
		return customerComplainDTOMapper;
		
	}
	
	@PostMapping("save")
	public Result<CustomerComplainDTO> save(@RequestBody CustomerComplainDTO customerComplainDTO){
		CustomerComplain obj= new CustomerComplain();
		Customer customer = new Customer();
	    customer.setId(customerComplainDTO.getCustomerId());
	    obj.setCustomer(customer);
		return super.save(customerComplainDTO);
	}
	
	 @GetMapping("get_all")
	 public Result<List<CustomerComplainDTO>> getAll() {
		 return super.getAll();
	 }
	 
	 @DeleteMapping("delete")
	 public Result deleteById(@RequestParam("id") long id) {
	     return super.deleteById(id);
	 }
	 
	 @GetMapping("/search")
	 public List<CustomerComplain> searchCustomerComplains(@RequestParam String complain) {
	     return customerComplainService.searchByComplain(complain);
	 }

}
