package com.altimetrik.schedule.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Schedule {
    @Id
    @GeneratedValue
    private int scheduleId;
    private Date departureDateTime;
    private Date arrivalDateTime;
    private int routeId;
    private int trainNo;
}