package com.rentcar.controller.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rentcar.domain.status.CarStatus;
import com.rentcar.domain.status.Transmission;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;



@ApiOperation("Class for updating car entity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {

    private String nameCar;

    private String model;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp releaseData;

    private String color;

    private Double vMotor;

    private Double power;

    private String linkPhoto;

    private Transmission transmission = Transmission.NOT_SELECTED;

    private Double costPerDay;

    private CarStatus carStatus = CarStatus.NOT_AVAILABLE;

    private Long discountId;
}
