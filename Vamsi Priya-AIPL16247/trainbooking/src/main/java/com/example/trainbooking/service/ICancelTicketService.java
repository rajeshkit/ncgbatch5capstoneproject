package com.example.trainbooking.service;

import com.example.trainbooking.entity.Booking;
import com.example.trainbooking.entity.CancelTicket;

public interface ICancelTicketService {
    CancelTicket cancelBooking(Booking booking);
}
