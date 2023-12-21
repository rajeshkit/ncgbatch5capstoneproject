package com.Booking.schedule.model;

import com.Booking.train.model.TrainResources;
import com.Booking.route.model.RouteResources;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name="Schedule_Table")
public class ScheduleResources {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "sequence", initialValue = 1000)
    private Long scheduleId;
    @NotNull(message="enter arrival date and time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalDateTime;
    @NotNull(message="enter departure date and time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureDateTime;
    @ManyToOne
    @JoinColumn(name="trainNumber")
    private TrainResources train;
    @ManyToOne
    @JoinColumn(name="routeId")
    private RouteResources route;
}
