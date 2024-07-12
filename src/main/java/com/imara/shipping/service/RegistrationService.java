package com.imara.shipping.service;

import com.imara.shipping.dto.*;
import com.imara.shipping.model.Customer;
import com.imara.shipping.model.Driver;
import com.imara.shipping.model.User;
import com.imara.shipping.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DriverService driverService;


    @Autowired
    private UserDTOMapper userDTOMapper;
    @Autowired
    private CustomerDTOMapper customerDTOMapper;
    @Autowired
    private DriverDTOMapper driverDTOMapper;



    public User registerUserWithCustomer(CustomerDTO customerDTO)
    {
       UserDTO userDTO = new UserDTO();
       Customer savedCustomer = customerService.save(customerDTOMapper.getEntity(customerDTO));
        userDTO.setPhoneNumber(customerDTO.getPhoneNumber());
        userDTO.setUserRole(UserRole.CUSTOMER);
        userDTO.setPasswordN(customerDTO.getPasswordN());
        userDTO.setPassword(customerDTO.getPasswordN());
        userDTO.setActive(true);
        userDTO.setFullName(customerDTO.getFullName());
        userDTO.setRefId(savedCustomer.getId());
        return userService.save(userDTOMapper.getEntity(userDTO));
    }

    public User registerUserWithDriver(DriverDTO driverDTO) {
        UserDTO userDTO = new UserDTO();
        Driver savedDriver = driverService.save(driverDTOMapper.getEntity(driverDTO));
        userDTO.setPhoneNumber(driverDTO.getPhoneNumber());
        userDTO.setUserRole(UserRole.DRIVER);
        userDTO.setPassword(driverDTO.getPasswordN());
        userDTO.setPasswordN(driverDTO.getPasswordN());
        userDTO.setActive(true);
        userDTO.setFullName(driverDTO.getFullName());
        userDTO.setRefId(savedDriver.getId());
        return userService.save(userDTOMapper.getEntity(userDTO));
    }

    public DriverDTO updateDriver(DriverDTO driverDTO) {
        UserDTO userDTO = userDTOMapper.getDTO(userService.findByRefId(driverDTO.getId()));
        Driver savedDriver = driverService.save(driverDTOMapper.getEntity(driverDTO));
        userDTO.setPhoneNumber(driverDTO.getPhoneNumber());
        userDTO.setUserRole(UserRole.DRIVER);
        userDTO.setActive(true);
        userDTO.setFullName(driverDTO.getFullName());
        userDTO.setRefId(savedDriver.getId());
        userService.save(userDTOMapper.getEntity(userDTO));
        return driverDTOMapper.getDTO(savedDriver);
    }

    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        UserDTO userDTO = userDTOMapper.getDTO(userService.findByRefId(customerDTO.getId()));
        Customer savedCustomer = customerService.save(customerDTOMapper.getEntity(customerDTO));
        userDTO.setPhoneNumber(customerDTO.getPhoneNumber());
        userDTO.setUserRole(UserRole.CUSTOMER);
        userDTO.setActive(true);
        userDTO.setFullName(customerDTO.getFullName());
        userDTO.setRefId(savedCustomer.getId());
        userService.save(userDTOMapper.getEntity(userDTO));
        return customerDTOMapper.getDTO(savedCustomer);
    }
}
