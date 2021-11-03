package com.rentcar.controller;


import com.rentcar.controller.requests.BillRequest;
import com.rentcar.controller.requests.OrderRequest;
import com.rentcar.service.BillService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

    public  final BillService billService;



    @ApiOperation(value = "create bill")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Users were successfully found")
    })

    @PostMapping("/create")
    public BillRequest create(@RequestBody BillRequest billRequest) {
        return billService.createBill(billRequest);
    }



    @ApiOperation(value = "delete bill")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Users were successfully found")
    })
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        billService.deleteBill(id);
    }


}
