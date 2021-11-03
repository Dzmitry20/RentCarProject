package com.rentcar.controller.requests.mappers;

import com.rentcar.controller.requests.BillRequest;
import com.rentcar.controller.requests.OrderRequest;
import com.rentcar.domain.Bill;
import com.rentcar.domain.Order;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BillMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBillFromBillRequest(BillRequest billRequest, @MappingTarget Bill bill);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBillRequestFromBill(Bill bill, @MappingTarget BillRequest billRequest);

}
