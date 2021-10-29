package com.rentcar.repository;

import com.rentcar.domain.Order;
import com.rentcar.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {







}
