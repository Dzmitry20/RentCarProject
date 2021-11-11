package com.rentcar.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorMessage {

    private  Long errorId;

    private  String errorMessage;


}
