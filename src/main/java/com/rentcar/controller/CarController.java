package com.rentcar.controller;


import com.rentcar.controller.requests.UserRequest;
import com.rentcar.domain.Car;
import com.rentcar.domain.Order;
import com.rentcar.domain.status.CarStatus;
import com.rentcar.service.CarService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EnumType;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService carService;


    @ApiOperation(value = "update one user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", dataType = "string", paramType = "query",
                    value = "", required = true),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true,
                    dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was successfully updated"),
            @ApiResponse(code = 500, message = "There is no user with such id")
    })
    @GetMapping("/q")
    public List<Car> findCars(@RequestParam CarStatus  status) {
        return carService.findByStatus(status);
    }


    @ApiOperation(value = "findAll")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "start", dataType = "timestamp", paramType = "query",
//                    value = "", required = true),
//            @ApiImplicitParam(name = "end", dataType = "timestamp", paramType = "query",
//                    value = "", required = true),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true,
                    dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was successfully updated"),
            @ApiResponse(code = 500, message = "There is no user with such id")
    })
    @GetMapping("/free")
    public List<Car> findAll(@RequestParam Timestamp startDate, @RequestParam  Timestamp endDate) {
        return carService.findAllCar(startDate,endDate);
    }

}
