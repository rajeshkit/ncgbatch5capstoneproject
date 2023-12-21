package com.altimetrik.train.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
        List<String> listOfError = fieldErrorList.stream().map(e -> e.getField() + " : " + e.getDefaultMessage()).collect(Collectors.toList());
        ApiError apiError = ApiError.builder()
                .path(request.getRequestURI())
                .message(listOfError)
                .status(HttpStatus.BAD_REQUEST.toString())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}
