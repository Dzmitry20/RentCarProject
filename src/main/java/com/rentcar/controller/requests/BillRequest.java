package com.rentcar.controller.requests;


import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiOperation("Class for creating bill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillRequest {

    private Integer numberBill;

    private Long OrderId;
}
