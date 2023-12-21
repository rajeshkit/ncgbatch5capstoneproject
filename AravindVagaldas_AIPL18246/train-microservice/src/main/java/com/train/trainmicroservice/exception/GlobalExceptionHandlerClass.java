package com.train.trainmicroservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Date;
import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandlerClass {

    @ExceptionHandler(TrainNumberNotExistsException.class)
    public ResponseEntity<APIError> handlerTrainNumberNotExistsException(TrainNumberNotExistsException e,HttpServletRequest request){
        APIError apiError=new APIError(new Date(),request.getRequestURI(), Collections.singletonList(e.getLocalizedMessage()),HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrainNumberAlreadyExistsException.class)
    public ResponseEntity<APIError> handlerTrainNumberNotExistsException(TrainNumberAlreadyExistsException e,HttpServletRequest request){
        APIError apiError=new APIError(new Date(),request.getRequestURI(), Collections.singletonList(e.getLocalizedMessage()),HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> handlerMethodArgumentNotValidException(MethodArgumentNotValidException m, HttpServletRequest request){
        List<FieldError> list=m.getBindingResult().getFieldErrors();
        List<String> listOfError=list.stream().map(e->e.getField()+" --> "+e.getDefaultMessage()).toList();
        APIError apiError=new APIError(new Date(),request.getRequestURI(),listOfError, HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage = "Request body is not readable: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}

