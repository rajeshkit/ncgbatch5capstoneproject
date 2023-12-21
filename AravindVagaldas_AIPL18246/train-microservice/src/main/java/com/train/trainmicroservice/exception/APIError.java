package com.train.trainmicroservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIError {
    private Date date;
    private String path;
    private List<String> message;
    private String status;
}
