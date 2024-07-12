package com.imara.shipping.repository;

import com.imara.shipping.model.PreferenceGroup;
import com.imara.shipping.repository.core.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PreferenceGroupRepository extends AbstractRepository<PreferenceGroup, Long> {
    @Query("SELECT pg FROM PreferenceGroup pg WHERE pg.name = :name and pg.id != :id")
    List<PreferenceGroup> findByNameAndSkipId(@Param("name") String name, @Param("id") long id);

    List<PreferenceGroup> findAllByParentId(long parentId);

    void deleteByParentId(long parentId);
}
