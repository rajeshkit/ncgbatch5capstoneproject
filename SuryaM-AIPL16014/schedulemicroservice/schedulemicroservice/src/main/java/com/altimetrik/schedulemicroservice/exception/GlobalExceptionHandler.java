package com.altimetrik.schedulemicroservice.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        ex.printStackTrace();
        String errorMessage = "Invalid JSON format. Please provide a valid JSON payload.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(InvalidFormatException.class)
//    public ResponseEntity<String> handleInvalidFormatException(InvalidFormatException ex) {
//        ex.printStackTrace();
//        String errorMessage = "Invalid date format. Please provide dates in the format 'yyyy-MM-dd HH:mm:ss'";
//        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
//    }
    @ExceptionHandler(ScheduleIdNotExistsException.class)
    public ResponseEntity<?> handlerScheduleIdNotExists(ScheduleIdNotExistsException ex, HttpServletRequest request) {

        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        ApiError apiError = ApiError.builder()
                .date(formattedDate)
                .path(request.getRequestURI())
                .message(Arrays.asList(ex.getLocalizedMessage()))
                .status(HttpStatus.NOT_FOUND.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> handleHttpClientErrorException(HttpClientErrorException ex, HttpServletRequest request) {

        String responseBody = ex.getResponseBodyAsString();
        String errorMessage = extractErrorMessage(responseBody);

        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        ApiError apiError = ApiError.builder()
                .date(formattedDate)
                .path(request.getRequestURI())
                .message(Arrays.asList(errorMessage))
                .status(String.valueOf(ex.getRawStatusCode())).build();
        return new ResponseEntity<>(apiError, ex.getStatusCode());
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
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex, HttpServletRequest request) {

        String errorMessage = "Internal Server Error";
        ex.printStackTrace();

        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        ApiError apiError = ApiError.builder()
                .date(formattedDate)
                .path(request.getRequestURI())
                .message(Arrays.asList("Internal Server Error"))
                .status(HttpStatus.INTERNAL_SERVER_ERROR.toString()).build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String extractErrorMessage(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            JsonNode messageNode = jsonNode.path("message");
            return messageNode.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
