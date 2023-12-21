package com.altimetrik.schedule.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ScheduleIdNotExistException.class)
    public ResponseEntity<?> handlerScheduleIdNotFound(ScheduleIdNotExistException ex, HttpServletRequest request) {

        ApiError apiError = ApiError.builder()
                .dateTime(LocalDateTime.from(LocalDateTime.now()))
                .path(request.getRequestURI())
                .message(Collections.singletonList(ex.getLocalizedMessage()))
                .status(HttpStatus.BAD_REQUEST.toString())
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
        List<String> listOfError = fieldErrorList.stream().map(e -> e.getField() + " : " + e.getDefaultMessage()).collect(Collectors.toList());
        ApiError apiError = ApiError.builder()
                .dateTime(LocalDateTime.from(LocalDateTime.now()))
                .path(request.getRequestURI())
                .message(listOfError)
                .status(HttpStatus.BAD_REQUEST.toString())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
