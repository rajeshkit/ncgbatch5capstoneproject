package com.finalproject.routemicroservice.exception;

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

public class RouteApiErrorclass {

    private Date ErrorDate ;

    private String path ;

    private String status ;

    private List<String> message ;
}
