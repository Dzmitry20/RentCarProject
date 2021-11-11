package com.rentcar.service;

import com.rentcar.controller.requests.BillRequest;
import com.rentcar.controller.requests.BillUpdateRequest;
import com.rentcar.controller.requests.mappers.BillMapper;
import com.rentcar.controller.requests.mappers.BillUpdateMapper;
import com.rentcar.domain.Bill;
import com.rentcar.domain.Car;
import com.rentcar.domain.Discount;
import com.rentcar.domain.Order;
import com.rentcar.domain.status.BillStatus;
import com.rentcar.domain.status.OrderStatus;
import com.rentcar.exception.NoSuchEntityException;
import com.rentcar.repository.BillRepository;
import com.rentcar.repository.CarRepository;
import com.rentcar.repository.DiscountRepository;
import com.rentcar.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

    public final BillRepository billRepository;

    public final OrderRepository orderRepository;

    public final CarRepository carRepository;

    public final BillMapper billMapper;

    public final BillUpdateMapper billUpdateMapper;

    public final DiscountRepository discountRepository;


    public int days(Timestamp d1, Timestamp d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public double totalPriceForOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Order not found by id " ));

        Car car = carRepository.findById(order.getCar().getId())
                .orElseThrow(() -> new NoSuchEntityException("Car not found by id "));

        Discount discount = discountRepository.findById(car.getDiscount().getId())
                .orElseThrow(() -> new NoSuchEntityException("Discount not found by id "));

        int days = days(order.getReceivedDate(), order.getReturnDate());

        double price;
        if ((discount.getStartDate().getTime() < order.getReceivedDate().getTime()) && (order.getReceivedDate().getTime() < discount.getEndDate().getTime())){
             price = car.getCostPerDay() * days - car.getCostPerDay() * days * discount.getPercentages() / 100;
        }else
            price = car.getCostPerDay() * days;

                 if (days > 7 && days <= 30) {
                     price *= 0.9;
                 } else if (days > 30 && days <= 150) {
                     price *= 0.8;
                 } else if (days > 150) {
                     price *= 0.7;
                 }
                 return price;

    }


    public BillRequest createBill(BillRequest billRequest) {

        Bill bill = new Bill();

        Order order = orderRepository.findById(billRequest.getOrderId())
                .orElseThrow(() -> new NoSuchEntityException("Order not found by id "));

        billMapper.updateBillFromBillRequest(billRequest, bill);
        bill.setCreateDate(new Timestamp(new Date().getTime()));
        bill.setBillStatus(BillStatus.AWAITING_PAYMENT);
        bill.setTotalPrice(totalPriceForOrder(billRequest.getOrderId()));
        bill.setOrder(order);

        billRepository.save(bill);

        billMapper.updateBillRequestFromBill(bill, billRequest);

        order.setOrderStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);

        return billRequest;
    }


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = SQLException.class)
    public void deleteBill(Long id){

       billRepository.delete(id);
    }


    public List<Bill> findAllBills() {
        List<Bill> bills = billRepository.findAll();
     return bills;
    }


    public BillUpdateRequest updateBill(Long id, BillUpdateRequest billUpdateRequest) {

        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Bill not found by id "));

        billUpdateMapper.updateBillFromBillUpdateRequest(billUpdateRequest, bill);
        bill.setCreateDate(new Timestamp(new Date().getTime()));
        billRepository.save(bill);

        BillUpdateRequest request = new BillUpdateRequest();
        billUpdateMapper.updateBillUpdateRequestFromBill(bill, request);

        return request;
    }
}