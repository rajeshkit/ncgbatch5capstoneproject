package com.route.routemicroservice.exception;

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


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RouteIdNotExistException.class)
    public ResponseEntity<APIError> handlerRouteIdNotExistException(RouteIdNotExistException e, HttpServletRequest request){
        APIError apiError=new APIError(new Date(),request.getRequestURI(), Arrays.asList(e.getLocalizedMessage()), HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RouteIdAlreadyExistException.class)
    public ResponseEntity<APIError> handlerRouteIdNotExistException(RouteIdAlreadyExistException e, HttpServletRequest request){
        APIError apiError=new APIError(new Date(),request.getRequestURI(), Arrays.asList(e.getLocalizedMessage()), HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> handlerMethodArgumentNotValidException(MethodArgumentNotValidException m, HttpServletRequest request){
        List<FieldError> list=m.getBindingResult().getFieldErrors();
        List<String> listOfError=list.stream().map(e->e.getField()+" --> "+e.getDefaultMessage()).toList();
        APIError apiError=new APIError(new Date(),request.getRequestURI(),listOfError, HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }


}
