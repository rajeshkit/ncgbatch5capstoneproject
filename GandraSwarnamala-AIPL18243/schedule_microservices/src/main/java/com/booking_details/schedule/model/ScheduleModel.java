package com.booking_details.schedule.model;

import com.booking_details.route.model.RouteModel;
import com.booking_details.train.model.TrainModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="schedule_details")
public class ScheduleModel {
    @Id
    private long scheduleId;
    private LocalDateTime arrivalDateTime;
    private LocalDateTime departureDateTime;
    @OneToOne
    @JoinColumn(name = "trainNumber")
    private TrainModel train;

    @OneToOne
    @JoinColumn(name = "routeId")
    private RouteModel route;
}

