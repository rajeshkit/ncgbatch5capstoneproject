package com.altimetrik.schedulemicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ScheduleRequest {

    @Id
    @GeneratedValue
    private int scheduleId;
    private Date departureDateTime;
    private Date arrivalDateTime;
	private int trainId;
	private int routeId;
}
