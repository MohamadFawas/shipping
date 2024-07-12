package com.imara.shipping.controller;

import com.imara.shipping.controller.core.AbstractController;
import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.*;
import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.User;
import com.imara.shipping.model.UserRole;
import com.imara.shipping.service.RegistrationService;
import com.imara.shipping.service.UserService;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
@Slf4j
@Transactional
public class UserController extends AbstractController<User, UserDTO> {

    @Autowired
    private UserService userService;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private UserDTOMapper userMapper;

    @GetMapping("get_all")
    public Result<List<UserDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping("get_all_by_user_role")
    public Result<List<UserDTO>> getAllByUserRole(@RequestParam("user_role") UserRole userRole) {
        List<User> users = userService.findAllByUserRole(userRole);
        return new Result(userMapper.getDTOList(users, UserDTO.DEFAULT));
    }

    @PostMapping("save")
    public Result<UserDTO> save(@RequestBody UserDTO dto) {
        // access.checkMUAccess(dto.getMedUnitID(), AccessType.ADMIN);
//        if (!(dto.getUserRole() == UserRole.ADMIN || dto.getUserRole() == UserRole.MASTER_ADMIN)) {
//            throw new RuntimeException("Allow MASTER_ADMIN and ADMIN users only");
//        }
        return super.save(dto);
    }

    @PostMapping("save_customer")
    public Result<UserDTO> saveCustomer(@RequestBody CustomerDTO customer) {
        User saveduser = registrationService.registerUserWithCustomer(customer);
        return new Result(userMapper.getDTO(saveduser, UserDTO.DEFAULT));
    }

    @PostMapping("save_driver")
    public Result<UserDTO> saveDriver(@RequestBody DriverDTO driverDTO) {
        User saveduser = registrationService.registerUserWithDriver(driverDTO);
        return new Result(userMapper.getDTO(saveduser, DriverDTO.DEFAULT));
    }

//    @PostMapping("company/register")
//    public Result<CompanyDTO> registerCompany(@RequestBody CompanyDTO companyDTO) {
//        User registeredCompany = registrationService.registerUserWithCompany(companyDTO);
//        return new Result(userMapper.getDTO(registeredCompany, CompanyDTO.DEFAULT));
//    }

    @PostMapping("update_active")
    public Result updateUserActive(@RequestBody UserDTO dto) {
        userService.updateUserActive(dto.getId(), dto.isActive(), true);
        return new Result();
    }

    @Override
    protected AbstractService getService() {
        return userService;
    }

    @Override
    protected AbstractDTOMapper getDTOMapper() {
        return userMapper;
    }

    @Override
    protected Logger getLog() {
        return log;
    }
}
