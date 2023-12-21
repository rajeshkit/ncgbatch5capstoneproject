package com.example.trainbooking.service;
import com.example.trainbooking.entity.Booking;
import com.example.trainbooking.entity.CancelTicket;
import com.example.trainbooking.repository.CancelTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelTicketService implements ICancelTicketService {

    private final CancelTicketRepository cancelTicketRepository;

    @Autowired
    public CancelTicketService(CancelTicketRepository cancelTicketRepository) {
        this.cancelTicketRepository = cancelTicketRepository;
    }
        public CancelTicket cancelBooking(Booking booking) {
            CancelTicket cancelTicket = new CancelTicket(booking);
            return cancelTicketRepository.save(cancelTicket);
        }
    }
