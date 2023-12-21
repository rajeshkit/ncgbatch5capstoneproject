package com.altimetrik.schedule.exception;

import com.altimetrik.train.exception.ApiError;
import com.altimetrik.train.exception.TrainNumberNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ScheduleIDNotFoundException.class)
    public ResponseEntity<ApiError> handlerScheduleIDNotFoundException(ScheduleIDNotFoundException exception, HttpServletRequest request){
        ApiError apiError=new ApiError
                (new Date(), request.getRequestURI(), Arrays.asList(exception.getLocalizedMessage()), HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TrainNumberNotFoundException.class)
    public ResponseEntity<ApiError> handlerTrainNumberNotFoundException(TrainNumberNotFoundException exception, HttpServletRequest request){
        ApiError apiError=new ApiError
                (new Date(), request.getRequestURI(), Arrays.asList(exception.getLocalizedMessage()), HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handlerMethodInvalidArgBindingException(MethodArgumentNotValidException ex, HttpServletRequest request){
        List<FieldError> errorList=ex.getBindingResult().getFieldErrors();
        List<String> listOfErrors=errorList.stream().map(e->e.getField() + ":" + e.getDefaultMessage()).toList();
        ApiError apiError=new ApiError
                (new Date(), request.getRequestURI(), listOfErrors, HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> handleNotFoundException(HttpClientErrorException.NotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Train or Route Id not Found");
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getStatusText());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage = "Request body is not readable: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
