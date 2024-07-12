package com.imara.shipping.controller;

import com.imara.shipping.controller.core.AbstractController;
import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.AdminPreferenceDTO;
import com.imara.shipping.dto.AdminPreferenceDTOMapper;
import com.imara.shipping.dto.PreferenceGroupDTO;
import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.AdminPreference;
import com.imara.shipping.service.AdminPreferenceService;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/admin_preference/")
@Transactional
public class AdminPreferenceController extends AbstractController<AdminPreference, AdminPreferenceDTO> {
    @Autowired
    private AdminPreferenceService apService;

    @Autowired
    private AdminPreferenceDTOMapper apDTOMapper;

    @GetMapping("get_all")
    public Result<List<AdminPreferenceDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping("get_by_id")
    public Result<AdminPreferenceDTO> getByID(@RequestParam("id") long id) {
        return super.getByID(id);
    }

    @PostMapping("save")
    public Result<AdminPreferenceDTO> save(@RequestBody AdminPreferenceDTO dto) {
        return super.save(dto);
    }

    @GetMapping("get_by_name")
    public Result<AdminPreferenceDTO> getByNameAndUserId(@RequestParam("name") String name) {
        AdminPreference ap = apService.findByName(name);
        return new Result(apDTOMapper.getDTO(ap, PreferenceGroupDTO.OVERVIEW));
    }

    @PutMapping("update_value_by_name")
    public Result updateByName(@RequestBody AdminPreferenceDTO dto) {
        apService.updateValueByName(dto.getName(), dto.getValue());
        return new Result();
    }

    @Override
    protected AbstractService getService() {
        return apService;
    }

    @Override
    protected AbstractDTOMapper getDTOMapper() {
        return apDTOMapper;
    }

    @Override
    protected Logger getLog() {
        return log;
    }
}
