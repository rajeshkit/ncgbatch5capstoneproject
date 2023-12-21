package com.schedule.schedulemicroservice.model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleRequest {
    @FutureOrPresent(message = "The departure date and time should not be past")
    @NotNull(message = "departure DateTime should not be null")
    private LocalDateTime departureDateTime;
    @Future(message = "The arrival date and time should not be past or present")
    @NotNull(message = "arrival DateTime should not be null")
    private LocalDateTime arrivalDateTime;

    @Positive(message = "train Number should not be negative")
    private int trainNumber;
    @Positive(message = "routeId should not be negative")
    private int routeId;
}
