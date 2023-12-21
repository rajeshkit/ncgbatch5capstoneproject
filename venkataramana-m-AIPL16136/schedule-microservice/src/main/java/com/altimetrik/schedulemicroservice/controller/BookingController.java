package com.altimetrik.schedulemicroservice.controller;
import com.altimetrik.schedulemicroservice.exception.BookingIdAlreadyExistsException;
import com.altimetrik.schedulemicroservice.exception.BookingNotFoundException;
import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.model.Booking;
import com.altimetrik.schedulemicroservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/booking-api")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    //booking?passengerName=venkat&scheduleId=153
    @PostMapping("/booking")
    public Booking addBooking(@RequestParam String passengerName, @RequestParam Long scheduleId) throws ScheduleIdNotExistsException, BookingIdAlreadyExistsException {
        return bookingService.addBooking(passengerName, scheduleId);
    }

    @GetMapping("/booking/{bookingId}")
    public Booking getBookingById(@PathVariable Long bookingId) throws BookingIdAlreadyExistsException {
        return bookingService.getBookingById(bookingId);
    }

    @GetMapping("/booking")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @DeleteMapping("/booking/{bookingId}")
    public String deleteBooking(@PathVariable Long bookingId) {
       return bookingService.deleteBooking(bookingId);
    }

}
