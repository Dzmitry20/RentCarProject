package com.rentcar.repository;

import com.rentcar.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select r.id as id, r.role_name as role_name from user_roles inner join roles r on r.id = user_roles.role_id where user_roles.user_id = :userId", nativeQuery = true)
    List<Role> findByUser(@Param("userId") Long id);


}
