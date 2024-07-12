package com.imara.shipping.repository;

import com.imara.shipping.model.ApprovalImage;
import com.imara.shipping.repository.core.AbstractRepository;

import java.util.List;

public interface ApprovalImageRepository extends AbstractRepository<ApprovalImage, Long> {
    List<ApprovalImage> getByApprovalId(long id);
}
