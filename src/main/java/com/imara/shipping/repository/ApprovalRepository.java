package com.imara.shipping.repository;

import com.imara.shipping.model.Approval;
import com.imara.shipping.repository.core.AbstractRepository;

public interface ApprovalRepository extends AbstractRepository<Approval,Long> {
    Approval findByShipmentId(long id);
}
