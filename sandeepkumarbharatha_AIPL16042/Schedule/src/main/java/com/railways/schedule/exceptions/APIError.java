package com.railways.schedule.exceptions;

import lombok.*;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIError {

    private Date date;
    private String path;
    private List<String> message;
    private String status;
}
