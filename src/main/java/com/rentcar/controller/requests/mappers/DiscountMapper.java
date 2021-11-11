package com.rentcar.controller.requests.mappers;

import com.rentcar.controller.requests.DiscountRequest;
import com.rentcar.controller.requests.UserRequest;
import com.rentcar.domain.Discount;
import com.rentcar.domain.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface DiscountMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDiscountFromDiscountRequest(DiscountRequest discountRequest, @MappingTarget Discount discount);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDiscountRequestFromDiscount(Discount discount, @MappingTarget DiscountRequest discountRequest);

}