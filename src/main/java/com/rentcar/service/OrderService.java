package com.rentcar.service;


import com.rentcar.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    public final OrderRepository orderRepository;
}
