package com.schedulemicroservice.exception;

public class ScheduleIdNotExistException extends Exception {
   private String s;

    public ScheduleIdNotExistException() {
    }

    public ScheduleIdNotExistException(String s) {
        super(s);
        this.s = s;
    }
}
