package com.rentcar.controller.requests.mappers;

import com.rentcar.controller.requests.BillUpdateRequest;
import com.rentcar.controller.requests.CarRequest;
import com.rentcar.domain.Bill;
import com.rentcar.domain.Car;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BillUpdateMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBillFromBillUpdateRequest(BillUpdateRequest billUpdateRequest, @MappingTarget Bill bill);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBillUpdateRequestFromBill(Bill bill, @MappingTarget BillUpdateRequest billUpdateRequest);

}