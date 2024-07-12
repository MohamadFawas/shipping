package com.imara.shipping.controller;

import com.imara.shipping.controller.core.AbstractController;
import com.imara.shipping.controller.core.Result;
import com.imara.shipping.dto.ApprovalDTO;
import com.imara.shipping.dto.ApprovalDTOMapper;
import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.Approval;
import com.imara.shipping.service.ApprovalService;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/approval/")
@Slf4j
@Transactional
public class ApprovalController extends AbstractController<Approval, ApprovalDTO> {
    @Autowired
    private ApprovalService approvalService;
    @Autowired
    private ApprovalDTOMapper approvalDTOMapper;

    @Override
    protected AbstractService getService() {
        return approvalService;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    @Override
    protected AbstractDTOMapper getDTOMapper() {
        return approvalDTOMapper;
    }

    @PostMapping("save")
    public Result<ApprovalDTO> save(@RequestBody ApprovalDTO approvalDTO) {
        return new Result(approvalService.save(approvalDTOMapper.getEntity(approvalDTO)));
    }

    @GetMapping("get_all")
    public Result<List<ApprovalDTO>> getAll() {
        return super.getAll();
    }

    @GetMapping("get_by_id")
    public Result<ApprovalDTO> getByID(@RequestParam("id") long id) {
        return super.getByID(id);
    }

    @PostMapping("delete")
    public Result deleteById(@RequestParam("id") long id) {
        return super.deleteById(id);
    }


    @PostMapping("approve")
    public Result<ApprovalDTO> approveApprovalRequest(@RequestParam("id") long id) {
        Approval approval = approvalService.findById(id);
        approval.setApproved(true);
        approvalService.save(approval);
        return new Result<>(approvalDTOMapper.getDTO(approval));
    }

}
