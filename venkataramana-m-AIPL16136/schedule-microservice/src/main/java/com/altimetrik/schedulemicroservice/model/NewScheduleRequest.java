package com.altimetrik.schedulemicroservice.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewScheduleRequest {
        private int scheduleId;
        private String departureDateTime;
        private String arrivalDateTime;
        private int trainNumber;
        private int routeId;
}
