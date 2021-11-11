package com.rentcar.controller.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rentcar.domain.Order;
import com.rentcar.domain.status.BillStatus;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@ApiOperation("Class for creating bill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillUpdateRequest {

    private Long numberBill;

    private Double totalPrice;

    private BillStatus billStatus;

    private Long orderId;
}
