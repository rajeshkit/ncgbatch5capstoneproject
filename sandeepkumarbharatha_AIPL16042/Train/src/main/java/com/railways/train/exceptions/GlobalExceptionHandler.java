package com.railways.train.exceptions;

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


        @ExceptionHandler(TrainNumberNotFound.class)
        public ResponseEntity<?> handlerTrainNumberNotFound(TrainNumberNotFound ex, HttpServletRequest request) {
            APIError apiError = new APIError
                    (new Date(), request.getRequestURI(), Arrays.asList(ex.getLocalizedMessage()), HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<?> handlerMethodInvalidArgBindingException
                (MethodArgumentNotValidException ex, HttpServletRequest request) {
            List<FieldError> dlist = ex.getBindingResult().getFieldErrors();
            List<String> listOfError = dlist.stream().map(e -> e.getField() + ":" + e.getDefaultMessage()).collect(Collectors.toList());
            APIError apiError = new APIError
                    (new Date(), request.getRequestURI(), listOfError, HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
    }
