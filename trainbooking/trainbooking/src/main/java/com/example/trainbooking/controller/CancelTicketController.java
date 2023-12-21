package com.example.trainbooking.controller;
import com.example.trainbooking.entity.Booking;
import com.example.trainbooking.entity.CancelTicket;
import com.example.trainbooking.service.ICancelTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cancel-ticket-api")
public class CancelTicketController {
    private final ICancelTicketService cancelTicketService;

    @Autowired
    public CancelTicketController(ICancelTicketService cancelTicketService) {
        this.cancelTicketService = cancelTicketService;
    }
        @PostMapping("/cancel")
        public ResponseEntity<String> cancelBooking(@RequestBody Booking booking) {
            CancelTicket cancelTicket = cancelTicketService.cancelBooking(booking);
            return ResponseEntity.ok("Booking canceled successfully. Cancel Ticket ID: " + cancelTicket.getId());
        }
}
