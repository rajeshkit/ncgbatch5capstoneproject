package com.altimetrik.train.trainrestapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandlerClass {

    @ExceptionHandler({TrainIDNotExistsException.class})
    public ResponseEntity<?> handlerTrainIdNotExists(TrainIDNotExistsException e, HttpServletRequest httpRequest) {
        ApiError apiError = new ApiError(new Date(), httpRequest.getRequestURI(), Arrays.asList(e.getLocalizedMessage()), HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
