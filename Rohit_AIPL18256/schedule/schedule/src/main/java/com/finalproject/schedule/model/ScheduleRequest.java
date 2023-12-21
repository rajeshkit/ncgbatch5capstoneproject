package com.finalproject.schedule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ScheduleRequest {
    @Id
    @GeneratedValue
    private int scheduleId ;
    private String arrivalDateTime ;
    private String departureDateTime ;
    private int trainNumber ;
    private int routeId ;

}
