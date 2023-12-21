package com.altimetrik.schedule.model;

import com.altimetrik.route.model.Route;
import com.altimetrik.train.model.Train;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity(name = "schedule")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator",sequenceName = "sequence",initialValue = 1000)
    private int scheduleId;
    @NotNull(message = "Enter the Departure Date & Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureDateTime;
    @NotNull(message = "Enter the Arrival Date & Time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     private LocalDateTime arrivalDateTime;

    @ManyToOne
    @JoinColumn(name = "trainNo")
    private Train train;
    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;

}
