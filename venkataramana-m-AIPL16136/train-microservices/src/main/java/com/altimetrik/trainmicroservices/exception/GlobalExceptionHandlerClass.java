package com.altimetrik.trainmicroservices.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandlerClass {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> listOfError = ex.getBindingResult().getFieldErrors().stream().map(e -> e.getField() + ": " + e.getDefaultMessage()).collect(Collectors.toList());

        ApiError apiError = ApiError.builder().path(request.getRequestURI()).message(listOfError).status(HttpStatus.BAD_REQUEST.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TrainNumberNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleTrainNumberNotExistsException(TrainNumberNotExistsException ex, HttpServletRequest request) {
        ApiError apiError = ApiError.builder().path(request.getRequestURI()).message(Collections.singletonList(ex.getLocalizedMessage())).status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrainNumberAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiError> handleTrainNumberAlreadyExistsException(TrainNumberAlreadyExistsException ex, HttpServletRequest request) {
        ApiError apiError = ApiError.builder().path(request.getRequestURI()).message(Collections.singletonList(ex.getLocalizedMessage())).status(HttpStatus.CONFLICT.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }


}
