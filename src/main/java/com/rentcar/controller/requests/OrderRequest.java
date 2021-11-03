package com.rentcar.controller.requests;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.rentcar.domain.status.OrderStatus;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@ApiOperation("Class for updating order entity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp receivedDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp returnDate;

    private OrderStatus orderStatus;

    private Long userId;

    private Long carId;

}
