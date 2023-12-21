package com.altimetrikfinalproject.schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class NewScheduleRequest {
    @Id
    @GeneratedValue
    private int schedule_id;
    private Date departureDateTime;
    private Date arrivalDateTime;
    @NotEmpty
    private int trainId;
    @NotEmpty
    private int routeId;

}
