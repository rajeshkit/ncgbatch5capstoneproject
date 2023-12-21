package com.railwaybooking.Schedule.exception;

import com.railwaybooking.Route.exception.RouteIdNotFoundException;
import com.railwaybooking.Train.exception.TrainNumberNotExistException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<?> handlerScheduleIdNotExists(ScheduleNotFoundException ex, HttpServletRequest request){
        ApiError apiError=ApiError.builder()
                .date(new Date())
                .path(request.getRequestURI())
                .message(Arrays.asList(ex.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodInvalidArgBindingException(MethodArgumentNotValidException ex, HttpServletRequest request){
        List<FieldError> dlist=ex.getBindingResult().getFieldErrors();
        List<String> listOfError=dlist.stream().map(e->e.getField()+":"+e.getDefaultMessage()).collect(Collectors.toList());
        ApiError apiError=ApiError.builder()
                .date(new Date())
                .path(request.getRequestURI())
                .message(listOfError)
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> handleNotFoundException(HttpClientErrorException.NotFound ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Train Number or Route Id not found");
    }

    @ExceptionHandler(RouteIdNotFoundException.class)
    public ResponseEntity<?> handlerRouteNotFindException(RouteIdNotFoundException ex, HttpServletRequest request){
        com.railwaybooking.Route.exception.ApiError apiError=com.railwaybooking.Route.exception.ApiError.builder()
                .date(new Date())
                .path(request.getRequestURI())
                .message(Arrays.asList(ex.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrainNumberNotExistException.class)
    public ResponseEntity<?> handlerTrainNumberNotExists(TrainNumberNotExistException ex, HttpServletRequest request){
        com.railwaybooking.Train.exception.ApiError apiError=com.railwaybooking.Train.exception.ApiError.builder()
                .date(new Date())
                .path(request.getRequestURI())
                .message(Arrays.asList(ex.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}
