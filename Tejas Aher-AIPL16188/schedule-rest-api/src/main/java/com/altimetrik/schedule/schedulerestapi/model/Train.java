package com.altimetrik.schedule.schedulerestapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Train {

    @Id
    private String trainNumber;
    private String trainName;
    private Integer totalKms;
    private String acCoaches;
    private Integer acCoachesTotalSeats;
    private String generalCoaches;
    private Integer generalCoachesTotalSeats;
}
