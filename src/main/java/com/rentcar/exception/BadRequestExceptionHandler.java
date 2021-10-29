package com.rentcar.exception;


import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
public class BadRequestExceptionHandler {

    //    private static final Logger logger = LoggerFactory.getLogger(BadRequestExceptionHandler.class);
//
//    /**
//     * Проверьте обработку ошибок
//     *
//     * @param исключение сбора информации об ошибках
//     * @return сообщение об ошибке
//     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ServiceResult validationBodyException(MethodArgumentNotValidException exception) {
//
//        BindingResult result = exception.getBindingResult();
//        if (result.hasErrors()) {
//
//            List<ObjectError> errors = result.getAllErrors();
//
//            errors.forEach(p -> {
//
//                FieldError fieldError = (FieldError) p;
//                logger.error("Data check failure : object{" + fieldError.getObjectName() + "},field{" + fieldError.getField() +
//                        "},errorMessage{" + fieldError.getDefaultMessage() + "}");
//
//            });
//
//        }
//        return ServiceResult.error(«Пожалуйста, заполните правильную информацию»);
//    }
//}
//    /**
//     * Ошибка преобразования типа параметра
//     *
//     * @param error error
//     * @return сообщение об ошибке
//     */
//    @ExceptionHandler(HttpMessageConversionException.class)
//    public ServiceResult parameterTypeException(HttpMessageConversionException exception){
//
//        logger.error(exception.getCause().getLocalizedMessage());
//        return ServiceResult.error («Ошибка преобразования типа»);
}

