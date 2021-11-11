package com.rentcar.controller.requests.mappers;

import com.rentcar.controller.requests.CarRequest;
import com.rentcar.controller.requests.DiscountRequest;
import com.rentcar.domain.Car;
import com.rentcar.domain.Discount;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCarFromCarRequest(CarRequest carRequest, @MappingTarget Car car);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCarRequestFromCar(Car car, @MappingTarget CarRequest carRequest);

}