package com.altimetrik.trainschedule.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class ScheduleRequest {
@Id
@GeneratedValue
    private int NewScheduleRequest;
    @NotNull(message = "Please enter the NewScheduleRequest")
    private Date departureDateTime;
    private Date ArrivalDateTime;
    private int trainNumber;
    @NotNull(message = "Please enter the trainNumber")
    private int routeId;
}
