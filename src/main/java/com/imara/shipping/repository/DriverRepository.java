package com.imara.shipping.repository;

import com.imara.shipping.model.Driver;
import com.imara.shipping.repository.core.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DriverRepository extends AbstractRepository<Driver, Long> {
    List<Driver> findAllByCityId(@Param("cityId") Long cityId);

    //@Query(value = "SELECT d.* FROM drivers d, vehicles v WHERE d.city_id = :cityId AND v.type_id = :vehicleTypeId", nativeQuery = true)
    @Query(value = "SELECT DISTINCT d.* FROM drivers d, vehicles v WHERE d.city_id = :cityId AND v.type_id = :vehicleTypeId AND d.id = v.driver_id", nativeQuery = true)
    List<Driver> findDriversByCityIdAndVehicleTypeId(@Param("cityId") Long cityId, @Param("vehicleTypeId") Long vehicleTypeId);

}
