package com.rentcar.controller;


import com.rentcar.controller.requests.OrderRequest;
import com.rentcar.controller.requests.UserCreateRequest;
import com.rentcar.controller.requests.UserRequest;
import com.rentcar.domain.Order;
import com.rentcar.domain.User;
import com.rentcar.service.OrderService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;



    @ApiOperation(value = "find all users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Users were successfully found")
    })
    @GetMapping("/admin")
    public List<Order> findAll() {
        return orderService.findAllOrders();
    }


    @ApiOperation(value = "update one order")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", dataType = "string", paramType = "path",
                    value = "id of user for update", required = true),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true,
                    dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was successfully updated"),
            @ApiResponse(code = 500, message = "There is no user with such id")
    })
    @PutMapping("/{orderId}")
    public OrderRequest update(@PathVariable("orderId") Long id, @RequestBody OrderRequest orderRequest) {
        return orderService.updateOrder(id, orderRequest);
    }


    @ApiOperation(value = "create order")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Users were successfully found")
    })

    @PostMapping("/create")
    public OrderRequest create(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }



    @ApiOperation(value = "delete order")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Users were successfully found")
    })
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
         orderService.deleteOrder(id);
    }



    @ApiOperation(value = "delete order with dependency")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Users were successfully found")
    })
    @DeleteMapping("/deleteWith/{id}")
    public void deleteOrderWith(@PathVariable("id") Long id) {
        orderService.deleteOrderWithDependency(id);
    }










}
