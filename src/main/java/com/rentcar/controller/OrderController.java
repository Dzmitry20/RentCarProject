package com.rentcar.controller;

import com.rentcar.controller.requests.OrderRequest;
import com.rentcar.domain.Order;
import com.rentcar.service.OrderService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;



    @ApiOperation(value = "find all orders")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Orders were successfully found")
    })
    @GetMapping("/admin")
    public List<Order> findAll() {
        return orderService.findAllOrders();
    }


    @ApiOperation(value = "update one order")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", dataType = "string", paramType = "path",
                    value = "id of order for update", required = true),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true,
                    dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order was successfully updated"),
            @ApiResponse(code = 500, message = "There is no order with such id")
    })
    @PutMapping("/{orderId}")
    public OrderRequest update(@PathVariable("orderId") Long id, @RequestBody OrderRequest orderRequest) {
        return orderService.updateOrder(id, orderRequest);
    }


    @ApiOperation(value = "create order")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order was successfully created"),
            @ApiResponse(code = 500, message = "Order was not created")
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
            @ApiResponse(code = 200, message = "Order was successfully deleted")
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
            @ApiResponse(code = 200, message = "Order was successfully deleted")
    })
    @DeleteMapping("/deleteWith/{id}")
    public void deleteOrderWith(@PathVariable("id") Long id) {
        orderService.deleteOrderWithDependency(id);
    }










}
