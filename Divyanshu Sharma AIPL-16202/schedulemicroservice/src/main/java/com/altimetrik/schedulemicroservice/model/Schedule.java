package com.altimetrik.schedulemicroservice.model;
import java.util.Date;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
We are fetching Data from train and route class to here
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {


    private int scheduleId;
    private Date departureDateTime;
    private Date arrivalDateTime;
    @ManyToOne
    @JoinColumn(name ="trainNumber")
    private Train train;
//    @ManyToOne
//    @JoinColumn(name = "routeId")
    private Route route;
}
