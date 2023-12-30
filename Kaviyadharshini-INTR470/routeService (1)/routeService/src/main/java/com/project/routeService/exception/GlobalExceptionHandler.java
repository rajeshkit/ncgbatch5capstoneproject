package com.project.routeService.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(RouteNotFoundException.class)
    public ResponseEntity<?> handlerRouteIsNotExistException(RouteNotFoundException rnx, HttpServletRequest hsrt) {

        RouteApiError routeApiError = RouteApiError.builder().ErrorDate(new Date()).message(Arrays.asList(rnx.getLocalizedMessage())).path(hsrt.getRequestURI()).status(HttpStatus.NOT_FOUND.toString()).build();

        return new ResponseEntity<>(routeApiError, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException max, HttpServletRequest hsrt) {

        List<FieldError> alist = max.getBindingResult().getFieldErrors();

        List<String> ls = alist.stream().map(e -> e.getField() + " " + e.getDefaultMessage()).collect(Collectors.toList());

        RouteApiError routeApiError = RouteApiError.builder().ErrorDate(new Date()).path(hsrt.getServletPath()).message(ls).status(HttpStatus.BAD_REQUEST.toString()).build();

        return new ResponseEntity<>(routeApiError, HttpStatus.BAD_REQUEST);


    }
}
