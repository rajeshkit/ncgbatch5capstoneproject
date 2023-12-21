package com.altimetrik.trainmicroservice.exception;
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

/*
@RestControllerAdvice : we can add this annotation to make this class as Global Exception handler Class

 */





@RestControllerAdvice
public class GlobalExceptionHandlerClass
{

    // Train Number Does not Exist Error Exception Handeling
    @ExceptionHandler(TrainNumberNotExistsException.class)
    public ResponseEntity<?> handlerTrainNumberNotExists(TrainNumberNotExistsException ex, HttpServletRequest request) {
        ApiError apiError = ApiError.builder().date(new Date()).path(request.getRequestURI()).message(Arrays.asList(ex.getLocalizedMessage())).status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodInvalidArgBindingException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        //Logic: We can have the exception in ex and through ex we can get the BindingResults and Fields Errors  and store them in a list of errors
        List<FieldError> fieldErrorsList = ex.getBindingResult().getFieldErrors();
        List<String> errorList = fieldErrorsList.stream().map(e->e.getField()+" : "+e.getDefaultMessage()).collect(Collectors.toList());
        //ApiError apiError = new ApiError(new Date(),request.getRequestURI(),errorList,HttpStatus.BAD_REQUEST.toString());
        ApiError apiError = ApiError.builder().date(new Date()).path(request.getRequestURI()).message(errorList).status(HttpStatus.BAD_REQUEST.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        //When the Error is printed >>> productCost : Please Enter a Positive product cost >>the productCost here comes from the [e.getField()] code.
    }


}
