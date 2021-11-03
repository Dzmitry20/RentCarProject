package com.rentcar.repository;

import com.rentcar.domain.Bill;
import com.rentcar.domain.Car;
import com.rentcar.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {


    @Modifying
    @Query(value = "delete from Bill b where b.order = :order")
    void delete(@Param("order") Order order);


    @Modifying
    @Query(value = "delete from Bill b where b.id = :billID")
    void delete(@Param("billID") Long id);
}
