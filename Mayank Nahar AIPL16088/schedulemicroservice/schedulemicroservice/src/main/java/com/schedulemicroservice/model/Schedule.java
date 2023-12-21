package com.schedulemicroservice.model;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Schedule {
    private int scheduleId;
    private String arrivalDateTime;
    private String departureDateTime;
    @ManyToOne
    @JoinColumn(name = "trainNumber")
    private Train train;
    private Route route;
}
