package com.railways.schedule.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.railways.route.model.Route;
import com.railways.train.model.Train;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Schedule {
    @Id
    @Positive(message = "enter positive value")
    private long scheduleId;
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp departureDateTime;
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp arrivalDateTime;
    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;
    @ManyToOne
    @JoinColumn(name = "trainNumber")
    private Train train;


}
