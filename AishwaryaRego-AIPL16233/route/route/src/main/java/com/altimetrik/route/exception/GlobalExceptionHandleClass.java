package com.altimetrik.route.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@RestControllerAdvice
public class GlobalExceptionHandleClass {
    @ExceptionHandler(RouteIdNotExistsException.class)
    public ResponseEntity<?> handlerRouteIdNotExists(RouteIdNotExistsException ex, HttpServletRequest request) {
        ApiError apiError=ApiError.builder()
                .path(request.getRequestURI())
                .message(Arrays.asList(ex.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RouteAlreadyExistsException.class)
    public ResponseEntity<?> handleRouteAlreadyExistsException(RouteAlreadyExistsException ex, HttpServletRequest request) {
        ApiError apiError = ApiError.builder()
                .path(request.getRequestURI())
                .message(Arrays.asList(ex.getLocalizedMessage()))
                .status(HttpStatus.CONFLICT.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
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
}
