package com.altimetrik.trainschedule.modle;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Component
public class Schedule {
    @Id
    @GeneratedValue
    private int scheduleId;
    @OneToOne
    private Train train;
    @OneToOne
    private Route route;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
}

