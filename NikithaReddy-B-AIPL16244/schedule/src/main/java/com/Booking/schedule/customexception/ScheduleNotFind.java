package com.Booking.schedule.customexception;

public class ScheduleNotFind extends Exception {
    String msg;
    public ScheduleNotFind() {
        super();
    }
    public ScheduleNotFind(String msg) {
        super(msg);
        this.msg = msg;
    }
}
