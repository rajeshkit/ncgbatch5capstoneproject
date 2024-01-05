package com.trainbooking.routemicroservices.exception;

import com.trainbooking.routemicroservices.model.ApiError;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodInvalidArgBindingException(MethodArgumentNotValidException exception, HttpServletRequest request) {

        List<FieldError> dlist = exception.getBindingResult().getFieldErrors();
        List<String> listOfError = dlist.stream().map(e -> e.getField() + " -> " + e.getDefaultMessage()).collect(Collectors.toList());

        ApiError apiError = new ApiError(new Date(), request.getRequestURI(), listOfError, HttpStatus.BAD_REQUEST.toString());

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RouteIdNotExistException.class)
    public ResponseEntity<?> handlerTrainNumberNotExistException(RouteIdNotExistException exception, HttpServletRequest request){
        ApiError apiError = new ApiError(new Date(), request.getRequestURI(), Arrays.asList(exception.getLocalizedMessage()), HttpStatus.NOT_FOUND.toString());

        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

}
