package com.rentcar.service;


import com.rentcar.domain.Car;
import com.rentcar.domain.Order;
import com.rentcar.domain.status.CarStatus;
import com.rentcar.repository.CarRepository;
import com.rentcar.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;

import javax.persistence.EnumType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public final OrderRepository orderRepository;


    public List<Car> findByStatus(CarStatus status) {

        return carRepository.findByCarStatus(status);
    }


    public List<Car> findAllCar(Timestamp startDate, Timestamp endDate) {

        List<Car> carList = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            if ((startDate.before(order.getReceivedDate()) && endDate.before(order.getReceivedDate())) || (startDate.after(order.getReturnDate()) && endDate.after(order.getReturnDate()))) {
                Car car = order.getCar();
                carList.add(car);
            }
        }
        return carList;
    }
}
