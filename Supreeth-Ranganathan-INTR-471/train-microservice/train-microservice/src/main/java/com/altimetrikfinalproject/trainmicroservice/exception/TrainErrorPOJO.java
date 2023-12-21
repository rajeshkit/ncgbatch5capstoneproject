package com.altimetrikfinalproject.trainmicroservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TrainErrorPOJO {
    private Date apiErrorDate;
    private String path;
    private List<String> message;
    private String status;
}
