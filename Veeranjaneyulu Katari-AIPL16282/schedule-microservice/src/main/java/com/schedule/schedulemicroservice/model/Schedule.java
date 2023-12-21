package com.schedule.schedulemicroservice.model;

import com.route.routemicroservice.model.Route;
import com.train.trainmicroservice.model.Train;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleId;
    private Timestamp departureDateTime;
    private Timestamp arrivalDateTime;
    @ManyToOne
    @JoinColumn(name="trainNumber")
    private Train train;
    @ManyToOne
    @JoinColumn(name="routeId")
    private Route route;
}
