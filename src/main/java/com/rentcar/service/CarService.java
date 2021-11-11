package com.rentcar.service;


import com.rentcar.controller.requests.CarRequest;
import com.rentcar.controller.requests.DiscountRequest;
import com.rentcar.controller.requests.mappers.CarMapper;
import com.rentcar.domain.Car;
import com.rentcar.domain.Discount;
import com.rentcar.domain.Order;
import com.rentcar.domain.status.CarStatus;
import com.rentcar.exception.NoSuchEntityException;
import com.rentcar.repository.BillRepository;
import com.rentcar.repository.CarRepository;
import com.rentcar.repository.DiscountRepository;
import com.rentcar.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EnumType;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public final OrderRepository orderRepository;

    public final CarMapper carMapper;

    public final BillRepository billRepository;

    public final DiscountRepository discountRepository;


    public List<Car> findByStatus(CarStatus status) {

        return carRepository.findByCarStatus(status);
    }


    public List<Car> findAllCarFree(Timestamp startDate, Timestamp endDate) {

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


    public CarRequest updateCar( Long id, CarRequest carRequest) {

        Car car = carRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Car not found by id "));

        carMapper.updateCarFromCarRequest(carRequest, car);
        carRepository.save(car);
        CarRequest request = new CarRequest();
        carMapper.updateCarRequestFromCar(car,request);
        return request;
    }


    public List<CarRequest>  findAllCars(){

        List<CarRequest> list = new ArrayList<>();
        List<Car> cars = carRepository.findAll();
        CarRequest carRequest  = new CarRequest();
        for (Car car: cars) {
            carMapper.updateCarRequestFromCar(car,carRequest);
            list.add(carRequest);
        }
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = SQLException.class)
    public void deleteCar(Long id){

        Car car = carRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Car not found by id "));

       Set<Order> orders = orderRepository.findByCar(car);

       for(Order order: orders) {
           billRepository.delete(order);
       }

        orderRepository.delete(car);
        carRepository.deleteCar(id);

    }


    public CarRequest createCar(CarRequest carRequest) {

        Car car = new Car();
        carMapper.updateCarFromCarRequest(carRequest, car);
        Discount discount = discountRepository.findById(carRequest.getDiscountId())
                        .orElseThrow(() -> new NoSuchEntityException("discount not found by id "));
        car.setDiscount(discount);
        carRepository.save(car);
        CarRequest request = new CarRequest();
        carMapper.updateCarRequestFromCar(car,request);
        return request;

    }

}
