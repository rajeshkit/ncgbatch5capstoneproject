package com.altimetrik.SCHEDULE.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class Schedule {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int scheduleId;

    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;

//    @JsonIgnore
//    private int trainId;
//    @JsonIgnore
//    private int routeId;

    @ManyToOne
    private Train train;
    @ManyToOne
    private Route route;



}
