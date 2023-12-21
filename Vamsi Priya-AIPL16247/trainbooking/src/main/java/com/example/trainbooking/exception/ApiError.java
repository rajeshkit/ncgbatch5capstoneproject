package com.example.trainbooking.exception;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
@Data
@AllArgsConstructor
public class ApiError {
    private Date on;
    private String message;
    private String cause;
}
