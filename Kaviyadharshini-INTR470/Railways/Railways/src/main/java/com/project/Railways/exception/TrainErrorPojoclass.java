package com.project.Railways.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainErrorPojoclass {
    private String path ;
    private List<String> msg ;
    private String status ;
    private Date thereisApiErrorDate ;

}

