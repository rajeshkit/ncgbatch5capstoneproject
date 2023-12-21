package com.altimetrik.trainmicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TrainNumberNotExistsException.class)
    public ResponseEntity<?> handlerProductIdNotExists(TrainNumberNotExistsException ex, HttpServletRequest request) {

        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        ApiError apiError=ApiError.builder()
                .date(formattedDate)
                .path(request.getRequestURI())
                .message(Arrays.asList(ex.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TrainNumberAlreadyExistsException.class)
    public ResponseEntity<?> handlerProductIdNotExists(TrainNumberAlreadyExistsException ex, HttpServletRequest request) {

        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        ApiError apiError=ApiError.builder()
                .date(formattedDate)
                .path(request.getRequestURI())
                .message(Arrays.asList(ex.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodInvalidArgBindingException
            (MethodArgumentNotValidException ex, HttpServletRequest request){
        List<FieldError> dlist = ex.getBindingResult().getFieldErrors();
        List<String> listOfError = dlist.stream().map(e->e.getField()+":"+e.getDefaultMessage()).collect(Collectors.toList());
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        ApiError apiError=ApiError.builder()
                .date(formattedDate)
                .path(request.getRequestURI())
                .message(listOfError)
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }
}
