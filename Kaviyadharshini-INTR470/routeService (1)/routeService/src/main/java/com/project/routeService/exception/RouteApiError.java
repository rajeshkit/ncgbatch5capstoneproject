package com.project.routeService.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RouteApiError {

    private Date ErrorDate ;

    private String path ;

    private String status ;

    private List<String> message ;
}

