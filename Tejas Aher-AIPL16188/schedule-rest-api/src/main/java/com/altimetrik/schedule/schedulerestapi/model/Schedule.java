package com.altimetrik.schedule.schedulerestapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Schedule {

    @Id
    private String scheduleId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalDateTime;
    @ManyToOne
    @JoinColumn(name = "trainNumber")
    private Train train;
    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;
}
