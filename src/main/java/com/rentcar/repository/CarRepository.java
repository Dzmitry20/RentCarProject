package com.rentcar.repository;

import com.rentcar.domain.Car;
import com.rentcar.domain.Role;
import com.rentcar.domain.status.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EnumType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {


        List<Car> findAll();

        List<Car> findByCarStatus(CarStatus status);

        @Modifying
        @Query(value = "select c from Car c left join c.orders o where o.receivedDate= ?1 AND o.returnDate= ?2")
        List<Car> findByFreeDate(@Param("startDate") Timestamp startDate, @Param("endDate")Timestamp endDate);










}
