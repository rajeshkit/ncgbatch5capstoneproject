package com.trainbooking.schedulemicroservices.exception;

import com.trainbooking.schedulemicroservices.model.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.Date;

public class GlobalExceptionHandler {

    @ExceptionHandler(ScheduleIdNotExistException.class)
    public ResponseEntity<?> handlerTrainNumberNotExistException(ScheduleIdNotExistException exception, HttpServletRequest request){
        ApiError apiError = new ApiError(new Date(), request.getRequestURI(), Arrays.asList(exception.getLocalizedMessage()), HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }
}
