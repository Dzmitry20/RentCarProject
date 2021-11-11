package com.rentcar.exception;


import com.rentcar.aspect.LoggingAspect;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger log = Logger.getLogger(LoggingAspect.class);

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorMessage> handleOthersException(Throwable e) {
        /* Handles all other exceptions. Status code 500. */
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(1L, e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
