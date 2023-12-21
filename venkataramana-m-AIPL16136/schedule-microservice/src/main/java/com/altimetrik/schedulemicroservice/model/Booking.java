package com.altimetrik.schedulemicroservice.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Booking {
    @Id
    @GeneratedValue(generator = "bookingId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "bookingId", initialValue = 1, sequenceName = "bookingId")
    private Long bookingId;

    @NotBlank(message = "Passenger name cannot be blank")
    private String passengerName;

    @NotNull(message = "Schedule is required")
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
