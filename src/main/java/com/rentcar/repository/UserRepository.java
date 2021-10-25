package com.rentcar.repository;

import com.rentcar.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


     User findByLogin(String login);




}
