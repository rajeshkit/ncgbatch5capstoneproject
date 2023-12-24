package com.project.Railways.exception;

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

    @ExceptionHandler(TrainNotFoundException.class)
    public ResponseEntity<?> handlerTrainIsNotExistException(TrainNotFoundException tx , HttpServletRequest hst ){


        TrainErrorPojoclass trainErrorPojoclass = TrainErrorPojoclass.builder().thereisApiErrorDate(new Date()).msg(Arrays.asList(tx.getLocalizedMessage())).path(hst.getRequestURI()).status(HttpStatus.NOT_FOUND.toString()).build();

        return new ResponseEntity<>(trainErrorPojoclass , HttpStatus.NOT_FOUND) ;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException mrg ,HttpServletRequest hst ){


        List<FieldError> alist = mrg.getBindingResult().getFieldErrors();

        List<String> ls = alist.stream().map(e->e.getField()+"  "+e.getDefaultMessage()).collect(Collectors.toList());

        TrainErrorPojoclass trainErrorPojoclass = TrainErrorPojoclass.builder().thereisApiErrorDate(new Date()).path(hst.getServletPath()).msg(ls).status(HttpStatus.BAD_REQUEST.toString()).build();

        return new ResponseEntity<>(trainErrorPojoclass , HttpStatus.BAD_REQUEST);
    }


}
