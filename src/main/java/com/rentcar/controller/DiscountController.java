package com.rentcar.controller;

import com.rentcar.controller.requests.DiscountRequest;
import com.rentcar.service.DiscountService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
public class DiscountController {

    public final DiscountService discountService;


    @ApiOperation(value = "update one discount")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "discountId", dataType = "string", paramType = "path",
                    value = "id of discount for update", required = true),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true,
                    dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Discount was successfully updated"),
            @ApiResponse(code = 500, message = "There is no discount with such id")
    })
    @PutMapping("/{discountId}")
    public DiscountRequest update(@PathVariable("discountId") Long id, @RequestBody DiscountRequest discountRequest) {
        return discountService.updateDiscount(id, discountRequest);
    }



    @ApiOperation(value = "find all discounts")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Discounts were successfully found")
    })
    @GetMapping("/admin")
    public List<DiscountRequest> findAll() {
        return discountService.findAll();
    }



    @ApiOperation(value = "remove discount from the database")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "discountId", dataType = "string", paramType = "path",
                    value = "id of discount for deleting from database", required = true),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true,
                    dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Discount was successfully deleted"),
            @ApiResponse(code = 500, message = "There is no discount with such id")
    })
    @DeleteMapping("/admin/{discountId}")
    public void delete(@PathVariable("discountId") Long id) {
        discountService.deleteDiscount(id);
    }




    @ApiOperation(value = "create one discount")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true,
                    dataType = "string", paramType = "header")
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Discount was successfully created"),
            @ApiResponse(code = 500, message = "Discount was not created")
    })
    @PostMapping("/create")
    public DiscountRequest create(@RequestBody DiscountRequest discountRequest) {
        return discountService.createDiscount(discountRequest);
    }
}
