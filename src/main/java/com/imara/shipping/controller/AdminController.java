package com.imara.shipping.controller;

import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.AdminModel;
import com.imara.shipping.service.AdminPreferenceService;
import com.imara.shipping.utility.EncryptionUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/admin/")
@Transactional
public class AdminController {
    @Autowired
    AdminPreferenceService adminPreferenceService;
    @Autowired
    private EncryptionUtility encrypt;

    @PostMapping("login")
    public Result<String> login(@RequestBody AdminModel adminModel) throws Exception {
        String systemUsername = adminPreferenceService.findByName("ADMIN_USERNAME").getValue();
        String systemPassword = adminPreferenceService.findByName("ADMIN_PASSWORD").getValue();
        String token = "";
        boolean authorized = false;
        String message;
        int status;
        if (systemUsername.equals(adminModel.getUsername()) && systemPassword.equals(adminModel.getPassword())) {
            status = 200;
            message = "Success";
            token = encrypt.encrypt(systemPassword);
            adminPreferenceService.updateValueByName("ADMIN_TOKEN", token);
        } else if (!systemUsername.equals(adminModel.getUsername())) {
            status = 401;
            message = "Unauthorized: Invalid Username";
        } else {
            status = 401;
            message = "Unauthorized: Invalid Password";
        }
        return new Result<>(token, status, message);
    }

    @PostMapping("token_login")
    public Result<Boolean> tokenLogin(@RequestParam String token) throws Exception {
        boolean authorized = false;
        int status;
        String message;
        String systemToken = encrypt.decrypt(adminPreferenceService.findByName("ADMIN_TOKEN").getValue());

        if (systemToken.equals(encrypt.decrypt(token))) {
            status = 200;
            message = "Success";
            authorized = true;

        } else {
            status = 401;
            message = "Unauthorized: Invalid Token";
        }
        return new Result<>(authorized, status, message);
    }
}
