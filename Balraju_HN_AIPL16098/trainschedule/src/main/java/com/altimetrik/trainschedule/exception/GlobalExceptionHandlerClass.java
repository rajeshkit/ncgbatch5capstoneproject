package com.altimetrik.trainschedule.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandlerClass {
    @ExceptionHandler(ScheduleIdNotFoundException.class)
    public ResponseEntity<?> handlerTrainNumberNotExists(ScheduleIdNotFoundException ex, HttpServletRequest request){
        ApiException apiException=ApiException.builder().apiErrorDate(new Date()).path(request.getRequestURI()).message(Arrays.asList(ex.getLocalizedMessage())).status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({TrainNumberNotFoundException.class, RouteIdNotFoundException.class})
    public ResponseEntity<?> handlerTrainNotExists(Exception ex, HttpServletRequest request) {
        ApiException apiException=ApiException.builder()
                .apiErrorDate(new Date())
                .path(request.getRequestURI())
                .message(Arrays.asList(ex.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
}
