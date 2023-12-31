package com.altimetrik.train.trainrestapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private Date date;
    private String path;
    private List<String> msg;
    private String status;

}
