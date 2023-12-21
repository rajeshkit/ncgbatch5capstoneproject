package com.booking.schedule.model;

import com.booking.route.model.RouteResources;
import com.booking.train.model.TrainResources;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "sequence", initialValue = 1000)
    private Long scheduleId;

    @NotNull(message = "Please Enter Departure Date Time: yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp departureDateTime;

    @NotNull(message = "Please Enter Arrival Date Time: yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp arrivalDateTime;

    @ManyToOne
    @JoinColumn(name = "trainNumber")
    @NotNull(message = "train should not be null")
    private TrainResources train;

    @ManyToOne
    @JoinColumn(name ="routeId")
    @NotNull(message = "route should not be null")
    private RouteResources route;
}
