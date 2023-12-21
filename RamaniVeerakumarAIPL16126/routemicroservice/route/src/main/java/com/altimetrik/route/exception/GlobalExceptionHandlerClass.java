package com.altimetrik.route.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandlerClass {
    @ExceptionHandler(RouteIdDoNotExitsException.class)
    public ResponseEntity<?> handlerProductIdNotExists(RouteIdDoNotExitsException ex, HttpServletRequest request) {
        ApiError apiError=ApiError.builder()
                .path(request.getRequestURI())
                .message(Arrays.asList(ex.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodInvalidArgBindingException
            (MethodArgumentNotValidException ex, HttpServletRequest request){
        List<FieldError> dlist = ex.getBindingResult().getFieldErrors();
        List<String> listOfError = dlist.stream().map(e->e.getField()+":"+e.getDefaultMessage()).collect(Collectors.toList());
        ApiError apiError=ApiError.builder()
                .path(request.getRequestURI())
                .message(listOfError)
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(
            NoSuchElementException ex, HttpServletRequest request) {
        ApiError apiError = ApiError.builder()
                .path(request.getRequestURI())
                .message(Collections.singletonList("No such element found"))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
