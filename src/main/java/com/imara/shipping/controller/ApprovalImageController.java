package com.imara.shipping.controller;

import com.imara.shipping.controller.core.AbstractController;
import com.imara.shipping.dto.ApprovalImageDTO;
import com.imara.shipping.dto.approvalImageDTOMapper;
import com.imara.shipping.dto.core.AbstractDTOMapper;
import com.imara.shipping.model.ApprovalImage;
import com.imara.shipping.service.ApprovalImageService;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/approval_image/")
@Slf4j
@Transactional
public class ApprovalImageController extends AbstractController<ApprovalImage, ApprovalImageDTO> {
    @Autowired
    private ApprovalImageService approvalImageService;
    @Autowired
    private approvalImageDTOMapper approvalimageDTOMapper;
    @Override
    protected AbstractService getService() {
        return approvalImageService;
    }

    @Override
    protected Logger getLog() {
        return log;
    }
    @Override
    protected AbstractDTOMapper getDTOMapper()
    {
        return approvalimageDTOMapper;
    }
}
