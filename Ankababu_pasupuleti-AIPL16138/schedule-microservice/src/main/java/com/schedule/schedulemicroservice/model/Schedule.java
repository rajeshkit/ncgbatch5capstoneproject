package com.schedule.schedulemicroservice.model;

import com.route.routemicroservice.model.Route;
import com.train.trainmicroservice.model.Train;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleId;
    private Date departureDateTime;
    private Date arrivalDateTime;
    @ManyToOne
    @JoinColumn(name="trainNumber")
    private Train train;
    @ManyToOne
    @JoinColumn(name="routeId")
    private Route route;
}
