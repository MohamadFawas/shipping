package com.imara.shipping.service;

import com.imara.shipping.model.ApprovalImage;
import com.imara.shipping.repository.ApprovalImageRepository;
import com.imara.shipping.repository.core.AbstractRepository;
import com.imara.shipping.service.core.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ApprovalImageService extends AbstractService<ApprovalImage> {
    @Autowired
    private ApprovalImageRepository approvalImageRepository;

    @Override
    protected AbstractRepository getRepository() {
        return approvalImageRepository;
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    public List<ApprovalImage> getImagesById(long id) {
        return approvalImageRepository.getByApprovalId(id);
    }
}
