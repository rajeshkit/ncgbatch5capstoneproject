package com.altimetrik.schedulemicroservice.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleInput {
        @Id
        private int id;
        private int trainId;
        private int routeId;
        @NotNull(message = "Please enter the Train schedule departure date")
        private LocalDateTime departureDateTime;
        @NotNull(message = "Please enter the Train schedule arrival date")
        private LocalDateTime arrivalDateTime;
    }


