package com.example.trainbooking.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class TrackRequest {

    private int totalSeats;

    private String source;

    private String destination;

    private String departureTime;

    private int fare;

    private int availableSeats;

    private Date departureDate;

    private String tracklineName;


    public TrackRequest()
    {
      new TrackRequest();
    }

}
