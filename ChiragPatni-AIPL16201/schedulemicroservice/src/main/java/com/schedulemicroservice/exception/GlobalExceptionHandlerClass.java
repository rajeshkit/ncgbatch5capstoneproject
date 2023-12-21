package com.schedulemicroservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Arrays;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandlerClass {
    @ExceptionHandler({TrainNotFoundException.class, RouteNotFoundException.class, ScheduleIdDoesNotExistException.class})
    public ResponseEntity<?> handlerNotFound(Exception ex, HttpServletRequest request) {
        ApiError apiError=ApiError.builder()
                .date(new Date())
                .path(request.getRequestURI())
                .message(Arrays.asList(ex.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
