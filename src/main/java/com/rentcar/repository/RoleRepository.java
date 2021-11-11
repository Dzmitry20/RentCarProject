package com.rentcar.repository;

import com.rentcar.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select r.id, r.role_name from user_roles u inner join roles r on r.id = u.role_id where u.users_id = :userId", nativeQuery = true)
    List<Role> findByUser(@Param("userId") Long id);


}
