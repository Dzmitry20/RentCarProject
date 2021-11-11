package com.rentcar.repository;

import com.rentcar.domain.Car;
import com.rentcar.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {


    @Modifying
    @Query(value = "delete from Order o where o.id = :orderID")
    void delete(@Param("orderID") Long id);

    @Modifying
    @Query(value = "delete from Order o where o.car = :car")
    void delete(@Param("car") Car car);

    Set<Order> findByCar(Car car);








}
