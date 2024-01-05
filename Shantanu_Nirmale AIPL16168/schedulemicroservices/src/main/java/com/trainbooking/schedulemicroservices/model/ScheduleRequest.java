package com.trainbooking.schedulemicroservices.model;

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
    @GeneratedValue  //To Autogenerate the scheduleId value
    private int scheduleId;
    private String departureDateTime;
    private String arrivalDateTime;
    private int trainNumber;
    private int routeId;

}
