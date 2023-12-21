package com.booking.schedule.exception;

import com.booking.train.exception.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandlerClass {
    @ExceptionHandler(ScheduleIdNotExistsException.class)
    public ResponseEntity<ApiError> handlerProductIdNotExists(ScheduleIdNotExistsException ex, HttpServletRequest request)
    {
        com.booking.train.exception.ApiError apiError= com.booking.train.exception.ApiError.builder().apiErrorDate(new Date())
                .path(request.getRequestURI())
                .message(Arrays.asList(ex.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handlerMethodInvalidArgumentBindingException(MethodArgumentNotValidException ex, HttpServletRequest request)
    {
        List<FieldError> dlist = ex.getBindingResult().getFieldErrors();
        List<String> listOfError = dlist.stream().map(e->e.getField()+":"+e.getDefaultMessage()).toList();
        com.booking.train.exception.ApiError apiError= ApiError.builder().apiErrorDate(new Date())
                .path(request.getRequestURI())
                .message(listOfError)
                .status(HttpStatus.BAD_REQUEST.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> handleNotFoundException(HttpClientErrorException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TrainNumber/RouteId not found");
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex){
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getStatusText());
    }
}
