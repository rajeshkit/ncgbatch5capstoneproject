package com.altimetrik.train.exception;

public class TrainNotExistsException extends Exception{
    String mgs;

    public TrainNotExistsException() {
        super();
    }

    public TrainNotExistsException(String mgs) {
        super(mgs);
        this.mgs = mgs;
    }
}
