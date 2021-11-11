package com.rentcar.exception;

import com.rentcar.aspect.LoggingAspect;
import org.apache.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandlers {

    private static final Logger log = Logger.getLogger(LoggingAspect.class);

    @ExceptionHandler(NoSuchEntityException.class)
    public ResponseEntity<ErrorMessage> handleNoSuchEntityException(NoSuchEntityException e) {
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(2L, e.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNoSuchEntityException(UserNotFoundException e) {
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(3L, e.getMessage()),
                HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorMessage> handleUsernameNotFoundException(AuthenticationException e) {
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(4L, e.getMessage()),
                HttpStatus.NOT_FOUND);
    }

}