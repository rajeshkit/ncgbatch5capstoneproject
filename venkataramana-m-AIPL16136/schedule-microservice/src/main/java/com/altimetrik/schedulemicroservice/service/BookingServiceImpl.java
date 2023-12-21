package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exception.BookingIdAlreadyExistsException;
import com.altimetrik.schedulemicroservice.exception.BookingNotFoundException;
import com.altimetrik.schedulemicroservice.exception.ScheduleIdNotExistsException;
import com.altimetrik.schedulemicroservice.model.Booking;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ScheduleService scheduleService;
    @Override
    public Booking addBooking(String passengerName, Long scheduleId) throws ScheduleIdNotExistsException, BookingIdAlreadyExistsException {
        Schedule schedule = scheduleService.getScheduleById(scheduleId);
        if (schedule == null) {
            String errorMessage = "Schedule not found with ID: " + scheduleId;
            logger.error(errorMessage);
            throw new ScheduleIdNotExistsException(errorMessage);
        }
        Booking booking = new Booking();
        booking.setPassengerName(passengerName);
        booking.setSchedule(schedule);
        Booking savedBooking = bookingRepository.save(booking);
        logger.info("Booking added successfully with ID: {}", savedBooking.getBookingId());
        return savedBooking;
    }
    @Override
    public Booking getBookingById(Long bookingId) throws BookingNotFoundException {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if (optionalBooking.isPresent()) {
            return optionalBooking.get();
        } else {
            throw new BookingNotFoundException("Booking not found with ID: " + bookingId);
        }
    }


    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public String deleteBooking(Long bookingId) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if (optionalBooking.isPresent()) {
            bookingRepository.deleteById(bookingId);
            logger.info("Booking with ID {} deleted successfully.", bookingId);
            return "Booking with ID " + bookingId + " deleted successfully.";
        } else {
            throw new BookingNotFoundException("Booking not found with ID: " + bookingId);
        }
    }


}
