package com.booking_details.train.exception;

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

    @ExceptionHandler(TrainIdNotFoundException.class)
    public ResponseEntity<?> handlerTrainNumberNotExistsException(TrainIdNotFoundException e,HttpServletRequest request){
        APIError apiError=new APIError(new Date(),request.getRequestURI(), Arrays.asList(e.getLocalizedMessage()),HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException m, HttpServletRequest request){
        List<FieldError> list=m.getBindingResult().getFieldErrors();
        List<String> listOfError=list.stream().map(e->e.getField()+" --> "+e.getDefaultMessage()).collect(Collectors.toList());
        APIError apiError=new APIError(new Date(),request.getRequestURI(),listOfError, HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }

}

