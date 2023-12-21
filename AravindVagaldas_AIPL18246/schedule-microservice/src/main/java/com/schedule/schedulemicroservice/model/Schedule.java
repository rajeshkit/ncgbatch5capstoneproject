package com.schedule.schedulemicroservice.model;

import com.route.routemicroservice.model.Route;
import com.train.trainmicroservice.model.Train;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "sequence", initialValue = 1000)
    private int scheduleId;
    @FutureOrPresent(message = "The departure date and time should not be past")
    @NotNull(message = "departure DateTime should not be null")

    private LocalDateTime departureDateTime;
    @Future(message = "The arrival date and time should not be past or present")
    @NotNull(message = "arrival DateTime should not be null")
    private LocalDateTime arrivalDateTime;
    @ManyToOne
    @JoinColumn(name = "trainNumber")
    @NotNull(message = "train should not be null")
        private Train train;
    @ManyToOne
    @JoinColumn(name = "routeId")
    @NotNull(message = "train should not be null")
    private Route route;
}
