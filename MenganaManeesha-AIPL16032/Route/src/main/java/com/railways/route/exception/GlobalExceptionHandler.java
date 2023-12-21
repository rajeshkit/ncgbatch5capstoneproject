package com.railways.route.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RouteNotFindException.class)
    public ResponseEntity<ApiError> handlerRouteNotFindException(RouteNotFindException exception, HttpServletRequest request)
    {
        ApiError apiError=ApiError.builder()
                .date(new Date())
                .path(request.getRequestURI())
                .message(Arrays.asList(exception.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RouteIdExistsException.class)
    public ResponseEntity<ApiError> handlerRouteIdExistsException(RouteIdExistsException exception, HttpServletRequest request)
    {
        ApiError apiError=ApiError.builder()
                .date(new Date())
                .path(request.getRequestURI())
                .message(Arrays.asList(exception.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handlerMethodInvalidArgBindingException(MethodArgumentNotValidException exception, HttpServletRequest request)
    {
        List<FieldError> dlist=exception.getBindingResult().getFieldErrors();
        List<String> listOfError=dlist.stream().map(e->e.getField()+":"+e.getDefaultMessage()).toList();
        ApiError apiError=ApiError.builder()
                .date(new Date())
                .path(request.getRequestURI())
                .message(listOfError)
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        // Handle the exception and return an appropriate response
        String errorMessage = "Request body is not readable: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
