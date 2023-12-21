package com.Schedule.schedule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Schedule {
    @Id
    @GeneratedValue
     private int scheduleId;
     private LocalDateTime departureDateTime;
     private LocalDateTime ArrivalDateTime;
     private int trainId;
     private int routeId;


}
