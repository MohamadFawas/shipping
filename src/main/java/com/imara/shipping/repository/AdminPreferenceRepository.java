package com.imara.shipping.repository;

import com.imara.shipping.model.AdminPreference;
import com.imara.shipping.repository.core.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminPreferenceRepository extends AbstractRepository<AdminPreference, Long> {

    @Query("SELECT up FROM AdminPreference up WHERE up.name = :name and up.id != :id")
    List<AdminPreference> findByNameAndSkipId(@Param("name") String name, @Param("id") long id);

    AdminPreference findByName(String name);
}
