package com.trainbooking.bookingmicroservices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookingRequest {
    @Id
    @GeneratedValue
    private int bookingId;
    private String bookingBy;
    private String email;
    private int trainNumber;
    private int routeId;
    private int scheduleId;
}
