package com.imara.shipping.repository;

import com.imara.shipping.model.StatusUpdatedTime;
import com.imara.shipping.repository.core.AbstractRepository;

public interface StatusUpdatedTimeRepository extends AbstractRepository<StatusUpdatedTime, Long> {
    StatusUpdatedTime findByShipmentId(long id);

    StatusUpdatedTime[] findAllByShipmentId(long id);
}
