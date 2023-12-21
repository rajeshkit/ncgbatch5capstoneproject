package com.altimetrik.trainbooking.exception;
import com.altimetrik.trainbooking.exception.ApiException;
import com.altimetrik.trainbooking.exception.NoSuchElementException;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice  // will handel the exception
public class GLobalExceptionHandlerClass {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handlreDosNotExist(NoSuchElementException ex, HttpServletRequest request) {
        ApiException apiError = ApiException.builder()
                .path(request.getRequestURI())
                .message(Arrays.asList(ex.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodInvalidArgBindingException
            (MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<FieldError> dlist = ex.getBindingResult().getFieldErrors();
        List<String> listOfError = dlist.stream().map(e -> e.getField() + ":" + e.getDefaultMessage()).collect(Collectors.toList());
        ApiException apiError = ApiException.builder()
                .path(request.getRequestURI())
                .message(listOfError)
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
































//    {
//        ApiError apiError = new ApiError
//                (new Date(), request.getRequestURI(), Arrays.asList(ex.getLocalizedMessage()), HttpStatus.NOT_FOUND.toString());
//        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handlerMethodInvalidArgBindingException
//            (MethodArgumentNotValidException ex, HttpServletRequest request){
//        List<FieldError> dlist = ex.getBindingResult().getFieldErrors();
//        List<String> listOfError = dlist.stream().map(e->e.getField()+":"+e.getDefaultMessage()).collect(Collectors.toList());
//        ApiError apiError=new ApiError
//                (new Date(),request.getRequestURI(),listOfError,HttpStatus.BAD_REQUEST.toString());
//        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
//    }
//
//}
