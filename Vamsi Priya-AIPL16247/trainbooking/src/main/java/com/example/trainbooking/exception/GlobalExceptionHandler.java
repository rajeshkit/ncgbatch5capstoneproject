package com.example.trainbooking.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = CustomerIdNotFoundException.class)
    public ResponseEntity<ApiError> handleCustomerIdNotFoundException(CustomerIdNotFoundException ex) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(), "Check your customer id!! Enter a valid customer Id");
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NoRecordFoundException.class)
    public ResponseEntity<String> handleNoRecordFoundExceptionHandler(NoRecordFoundException ex)
    {
        String message = ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmptyInputException.class)
    public ResponseEntity<String> handleEmptyInputExceptionHandler(EmptyInputException ex)
    {
        String message = ex.getMessage();
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }

}
