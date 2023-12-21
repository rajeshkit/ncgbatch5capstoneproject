package com.altimetrikfinalproject.routemicroservice.exception;

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

    @ExceptionHandler(RouteDoesNotExistException.class)
    public ResponseEntity<?> handlerTrainDoesNotExistException(RouteDoesNotExistException ex, HttpServletRequest hsr){
        RouteApiError routeApiError = RouteApiError.builder().apiErrorDate(new Date()).message(Arrays.asList(ex.getLocalizedMessage())).path(hsr.getRequestURI()).status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(routeApiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodInvalidArgumentBindingException(MethodArgumentNotValidException ex, HttpServletRequest hsr){
        List<FieldError> dlist= ex.getBindingResult().getFieldErrors();
        List<String> ls = dlist.stream().map(e->e.getField()+ " "+ e.getDefaultMessage()).collect(Collectors.toList());
        RouteApiError routeApiError = RouteApiError.builder().apiErrorDate(new Date()).path(hsr.getServletPath()).message(ls).status(HttpStatus.BAD_REQUEST.toString()).build();
        return new ResponseEntity<>(routeApiError, HttpStatus.BAD_REQUEST);
    }
}
