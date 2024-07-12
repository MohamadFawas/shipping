package com.imara.shipping.repository;

import com.imara.shipping.model.SystemPreference;
import com.imara.shipping.repository.core.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SystemPreferenceRepository extends AbstractRepository<SystemPreference, Long> {
    @Query("SELECT sp FROM SystemPreference sp WHERE sp.name = :name and sp.id != :id")
    List<SystemPreference> findByNameAndSkipId(@Param("name") String name, @Param("id") long id);

    List<SystemPreference> findAllByPreferenceGroupId(long preferenceGroupId);

    SystemPreference findByName(String name);
}
