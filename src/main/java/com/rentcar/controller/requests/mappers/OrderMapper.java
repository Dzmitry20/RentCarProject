package com.rentcar.controller.requests.mappers;

import com.rentcar.controller.requests.OrderRequest;
import com.rentcar.controller.requests.UserRequest;
import com.rentcar.domain.Order;
import com.rentcar.domain.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrderFromOrderRequest(OrderRequest orderRequest, @MappingTarget Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrderRequestFromOrder(Order order, @MappingTarget OrderRequest orderRequest);

}
