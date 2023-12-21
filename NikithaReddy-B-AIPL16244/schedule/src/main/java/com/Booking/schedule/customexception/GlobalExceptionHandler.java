package com.Booking.schedule.customexception;

import com.Booking.route.customexception.RouteNotFindException;
import com.Booking.train.customexception.TrainIdNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class GlobalExceptionHandler {
    @ExceptionHandler(ScheduleNotFind.class)
    public ResponseEntity<?> handlerScheduleIdNotExists(ScheduleNotFind exception, HttpServletRequest request)
    {
        ApiError apiError=ApiError.builder()
                .date(new Date())
                .path(request.getRequestURI())
                .message(Arrays.asList(exception.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodInvalidArgBindingException(MethodArgumentNotValidException exception, HttpServletRequest request)
    {
        List<FieldError> dlist=exception.getBindingResult().getFieldErrors();
        List<String> listOfError=dlist.stream().map(e->e.getField()+":"+e.getDefaultMessage()).collect(Collectors.toList());
        ApiError apiError=ApiError.builder()
                .date(new Date())
                .path(request.getRequestURI())
                .message(listOfError)
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> handleNotFoundException(HttpClientErrorException.NotFound ex) {
        // Handle 404 Not Found error
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex) {
        // Handle other HttpClientErrorException
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getStatusText());
    }
    @ExceptionHandler(RouteNotFindException.class)
    public ResponseEntity<?> handlerRouteNotFindException(RouteNotFindException exception, HttpServletRequest request)
    {
        com.Booking.route.customexception.ApiError apiError= com.Booking.route.customexception.ApiError.builder()
                .date(new Date())
                .path(request.getRequestURI())
                .message(Arrays.asList(exception.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrainIdNotFoundException.class)
    public ResponseEntity<?> handlerTrainNumberNotExists(TrainIdNotFoundException exception, HttpServletRequest request)
    {
        com.Booking.train.customexception.ApiError apiError= com.Booking.train.customexception.ApiError.builder()
                .date(new Date())
                .path(request.getRequestURI())
                .message(Arrays.asList(exception.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
