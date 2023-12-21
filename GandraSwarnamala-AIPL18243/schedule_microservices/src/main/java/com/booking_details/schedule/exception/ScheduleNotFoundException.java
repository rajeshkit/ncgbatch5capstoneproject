package com.booking_details.schedule.exception;

public class ScheduleNotFoundException extends Exception {
    public ScheduleNotFoundException() {
        super("No Schedule details found with the given id");
    }

}
