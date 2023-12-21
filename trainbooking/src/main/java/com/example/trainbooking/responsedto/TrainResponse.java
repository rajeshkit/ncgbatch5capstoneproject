package com.example.trainbooking.responsedto;

import lombok.Getter;

import java.sql.Date;

public class TrainResponse
{

    @Getter
    private final int totalSeats;

    @Getter
    private final String source;

    @Getter
    private final String destination;

    @Getter
    private final String departureTime;

    @Getter
    private final int fare;

    @Getter
    private final int availableSeats;
    @Getter
    private final Date departureDate;

    private final String tracklineName;

    @Getter
    private final Date dateOfOperation;

    public TrainResponse(int totalSeats, String source, String destination, String departureTime, int fare, int availableSeats, Date departureDate, String name, Date dateOfOperation) {
        this.totalSeats = totalSeats;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.fare = fare;
        this.availableSeats = availableSeats;
        this.departureDate = departureDate;
        this.tracklineName = name;
        this.dateOfOperation = dateOfOperation;
    }

    public String getName() {
        return tracklineName;
    }

}