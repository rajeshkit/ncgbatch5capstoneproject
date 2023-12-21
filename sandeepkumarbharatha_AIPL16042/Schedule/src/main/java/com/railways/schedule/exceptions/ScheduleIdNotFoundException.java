package com.railways.schedule.exceptions;

public class ScheduleIdNotFoundException extends  Exception {
    String msg;

    public ScheduleIdNotFoundException() {
        super();
    }
    public ScheduleIdNotFoundException(String msg) {
        super(msg);
        this.msg = msg;

    }
}
