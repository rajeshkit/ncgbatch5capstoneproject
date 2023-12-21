package com.example.trainbooking.responsedto;
import lombok.Getter;

import java.sql.Date;
@Getter
public class TracklineResponse {
    private String name;

    private final Date dateOfOperation;

    public TracklineResponse(String name, Date dateOfOperation) {
        this.name = name;
        this.dateOfOperation = dateOfOperation;
    }

    public void setName(String name) {
        this.name = name;
    }
}