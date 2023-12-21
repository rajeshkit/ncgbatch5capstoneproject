package com.schedulemicroservice.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int scheduleId;

    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainNumber")
    private Train train;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "routeId")
    private Route route;
}
