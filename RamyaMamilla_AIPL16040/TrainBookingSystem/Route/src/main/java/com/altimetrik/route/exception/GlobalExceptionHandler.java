package com.altimetrik.route.exception;

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
    @ExceptionHandler(RouteIDNotFoundException.class)
    public ResponseEntity<ApiError> handlerRouteIDNotFoundException(RouteIDNotFoundException exception, HttpServletRequest request){
        ApiError apiError=new ApiError
                (new Date(), request.getRequestURI(), Arrays.asList(exception.getLocalizedMessage()), HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handlerMethodInvalidArgBindingException(MethodArgumentNotValidException ex,HttpServletRequest request){
        List<FieldError> errorList=ex.getBindingResult().getFieldErrors();
        List<String> listOfErrors=errorList.stream().map(e->e.getField() + ":" + e.getDefaultMessage()).toList();
        ApiError apiError=new ApiError
                (new Date(), request.getRequestURI(), listOfErrors, HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }//HttpMessageNotReadableException handler code
}
