package com.schedulemicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class ScheduleRequest {
    @Id
    private int scheduleId;
    @NotNull
    private int trainId;
    @NotNull
    private int routeId;
    @NotNull
    private String departureDateTime;
    @NotNull
    private String arrivalDateTime;


}
