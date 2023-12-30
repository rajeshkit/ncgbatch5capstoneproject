package com.project.Schedule.exception;

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

public class ScheduleError {

    private Date ErrorDate ;
    private String status ;
    private List<String> message ;
    private String path ;



}

