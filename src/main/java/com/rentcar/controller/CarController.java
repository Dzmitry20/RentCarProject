package com.rentcar.controller;

import com.rentcar.controller.requests.CarRequest;
import com.rentcar.domain.Car;
import com.rentcar.domain.status.CarStatus;
import com.rentcar.service.CarService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService carService;


    @ApiOperation(value = "find cars")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", dataType = "string", paramType = "query",
                    value = "status for search", required = true),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true,
                    dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cars were successfully found "),
            @ApiResponse(code = 500, message = "Cars were not found")
    })
    @GetMapping("/status")
    public List<Car> findCars(@RequestParam CarStatus  status) {
        return carService.findByStatus(status);
    }


    @ApiOperation(value = "findAll free cars")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", dataType = "timestamp", paramType = "query",
                    value = "startDate for order", required = true),
            @ApiImplicitParam(name = "endDate", dataType = "timestamp", paramType = "query",
                    value = "endDate for order", required = true),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true,
                    dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cars were successfully found"),
    })
    @GetMapping("/free")
    public List<Car> findAllFreeCars(@RequestParam Timestamp startDate, @RequestParam  Timestamp endDate) {
        return carService.findAllCarFree(startDate,endDate);
    }


    @ApiOperation(value = "update one car")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carId", dataType = "string", paramType = "path",
                    value = "id of car for update", required = true),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true,
                    dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Car was successfully updated"),
            @ApiResponse(code = 500, message = "There is no car with such id")
    })
    @PutMapping("/{carId}")
    public CarRequest update(@PathVariable("carId") Long id,  @RequestBody CarRequest carRequest) {
        return carService.updateCar(id, carRequest);
    }



    @ApiOperation(value = "find all cars")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cars were successfully found")
    })
    @GetMapping("/admin")
    public List<CarRequest> findAll() {
        return carService.findAllCars();
    }



    @ApiOperation(value = "remove car from the database")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carId", dataType = "string", paramType = "path",
                    value = "id of car for deleting from database", required = true),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true,
                    dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Car was successfully deleted"),
            @ApiResponse(code = 500, message = "There is no car with such id")
    })
    @DeleteMapping("/admin/{carId}")
    public void delete(@PathVariable("carId") Long id) {
        carService.deleteCar(id);
    }



    @ApiOperation(value = "create one car")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true,
                    dataType = "string", paramType = "header")
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Car was successfully created"),
            @ApiResponse(code = 500, message = "")
    })
    @PostMapping("/create")
    public CarRequest create(@RequestBody CarRequest carRequest) {
        return carService.createCar(carRequest);
    }


}
