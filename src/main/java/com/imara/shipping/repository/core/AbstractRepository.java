package com.imara.shipping.repository.core;

import com.imara.shipping.model.core.AbstractObject;
import org.springframework.data.repository.*;

@NoRepositoryBean
public interface AbstractRepository<T extends AbstractObject, ID> extends CrudRepository<T , Long> {

}
