package com.railways.schedule.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.railways.route.model.Route;
import com.railways.train.model.Train;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "schedule_resources")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "sequence", initialValue = 100)
    private Long scheduleId;
    @FutureOrPresent(message = "Enter Arrival Date and Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalDateTime;
    @FutureOrPresent(message = "Enter Departure Date and Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureDateTime;
    @ManyToOne
    @JoinColumn(name = "trainNumber")
    private Train train;
    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;
}
