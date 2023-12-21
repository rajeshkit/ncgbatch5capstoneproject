package com.TrainBookingSystem.Train.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private Date date;
    private String path;
    private List<String> message;
    private String status;
}
