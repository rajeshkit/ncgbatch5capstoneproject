package com.example.trainbooking.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "cancel_tickets")
public class CancelTicket {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "booking_id", nullable = false)
        private Booking booking;
        public CancelTicket(Booking booking) {
            this.booking = booking;
        }
    }

