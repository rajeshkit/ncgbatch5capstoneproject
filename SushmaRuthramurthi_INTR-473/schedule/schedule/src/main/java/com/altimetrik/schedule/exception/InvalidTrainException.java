package com.altimetrik.schedule.exception;

public class InvalidTrainException extends Exception{
    String mgs;

    public InvalidTrainException() {
        super();
    }

    public InvalidTrainException(String mgs) {
        super(mgs);
        this.mgs = mgs;
    }
}
