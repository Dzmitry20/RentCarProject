package com.rentcar.service;

import com.rentcar.controller.requests.OrderRequest;
import com.rentcar.controller.requests.mappers.OrderMapper;
import com.rentcar.domain.Car;
import com.rentcar.domain.Order;
import com.rentcar.domain.User;
import com.rentcar.exception.NoSuchEntityException;
import com.rentcar.repository.BillRepository;
import com.rentcar.repository.CarRepository;
import com.rentcar.repository.OrderRepository;
import com.rentcar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    public final OrderRepository orderRepository;

    public final OrderMapper orderMapper;

    public final UserRepository userRepository;

    public final CarRepository carRepository;

    public final BillRepository billRepository;


    public List<Order>  findAllOrders(){
        return orderRepository.findAll();
    }


    public OrderRequest updateOrder(Long id, OrderRequest orderRequest){

        Order order = orderRepository.findById(id).
                orElseThrow(() -> new NoSuchEntityException("Order not found by id " + id));

        orderMapper.updateOrderFromOrderRequest(orderRequest, order);

        orderRepository.save(order);

        OrderRequest request = new OrderRequest();

        orderMapper.updateOrderRequestFromOrder(order,request);

        return request;
    }


    public OrderRequest createOrder(OrderRequest orderRequest){

        Order order = new Order();

        orderMapper.updateOrderFromOrderRequest(orderRequest, order);

        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new NoSuchEntityException("User not found"));

        Car car = carRepository.findById(orderRequest.getCarId())
                .orElseThrow(() -> new NoSuchEntityException("User not found"));

        order.setUser(user);
        order.setCar(car);

        orderRepository.save(order);

        OrderRequest request = new OrderRequest();
        request.setUserId(order.getUser().getId());
        request.setCarId(order.getCar().getId());

        orderMapper.updateOrderRequestFromOrder(order,request);

        return request;

    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = SQLException.class)
    public void deleteOrder(Long id){

        orderRepository.delete(id);

    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = SQLException.class)
    public void deleteOrderWithDependency(Long id){

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Order not found by id " + id));

        billRepository.delete(order);
        orderRepository.delete(id);

    }
}
