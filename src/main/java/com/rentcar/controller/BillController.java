package com.rentcar.controller;

import com.rentcar.controller.requests.BillRequest;
import com.rentcar.controller.requests.BillUpdateRequest;
import com.rentcar.domain.Bill;
import com.rentcar.service.BillService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
            @ApiResponse(code = 200, message = "Bill was successfully created")
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
            @ApiResponse(code = 200, message = "Bill was successfully deleted")
    })
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        billService.deleteBill(id);
    }



    @ApiOperation(value = "find all bills")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Bills were successfully found")
    })
    @GetMapping("/admin")
    public List<Bill> findAll() {
        return billService.findAllBills();
    }



    @ApiOperation(value = "update one bill")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "billId", dataType = "string", paramType = "path",
                    value = "id of bill for update", required = true),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true,
                    dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Bill was successfully updated"),
            @ApiResponse(code = 500, message = "There is no bill with such id")
    })
    @PutMapping("/{billId}")
    public BillUpdateRequest update(@PathVariable("billId") Long id, @RequestBody BillUpdateRequest billUpdateRequest) {
        return billService.updateBill(id, billUpdateRequest);
    }

}
