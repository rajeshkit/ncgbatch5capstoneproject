package com.altimetrik.route.routerestapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandlerClass {

    @ExceptionHandler(RouteNumberNotFoundException.class)
    public ResponseEntity<?> handlerForProductIdNotExists(RouteNumberNotFoundException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(new Date(), request.getRequestURI(),
                Arrays.asList(ex.getLocalizedMessage()), HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> handlerMethodInvalidArgBindingException(MethodArgumentNotValidException e,HttpServletRequest request){
        ApiError apiError = new ApiError(new Date(),request.getRequestURI(),
                Arrays.asList(e.getLocalizedMessage()),HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

}
