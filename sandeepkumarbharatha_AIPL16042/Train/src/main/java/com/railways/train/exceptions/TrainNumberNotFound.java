package com.railways.train.exceptions;

public class TrainNumberNotFound  extends  Exception{
    String msg;
    public TrainNumberNotFound() {super();
    }
    public TrainNumberNotFound(String msg) {
        super(msg);
        this.msg = msg;
    }
}
