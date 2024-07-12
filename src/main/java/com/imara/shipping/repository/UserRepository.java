package com.imara.shipping.repository;

import com.imara.shipping.model.User;
import com.imara.shipping.model.UserRole;
import com.imara.shipping.repository.core.AbstractRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends AbstractRepository<User, Long> {
    User findByUsername(String username);


    // @Query("SELECT u FROM User u WHERE u.userRole = :userRole")
    // List<User> findAllByUserRole(@Param("userRole") UserRole userRole);
    List<User> findAllByUserRole(UserRole userRole);

    @Query("SELECT u FROM User u WHERE u.username = :username and u.id != :id")
    List<User> findByUsernameAndSkipId(@Param("username") String username, @Param("id") long id);

    @Modifying
    @Query("UPDATE User u SET u.active = :active where u.id = :id")
    void updateUserActive(@Param("id") long id, @Param("active") boolean active);

    User findByRefId(long refId);

}
