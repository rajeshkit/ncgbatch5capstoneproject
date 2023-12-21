package com.altimetrik.route.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionNotExist {
    @ExceptionHandler(RouteNotExistsException.class)
    public ResponseEntity<?> handlerRouteNotExists(RouteNotExistsException ex){
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
