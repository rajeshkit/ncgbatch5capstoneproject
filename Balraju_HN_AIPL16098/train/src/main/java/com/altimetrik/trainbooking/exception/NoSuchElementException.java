package com.altimetrik.trainbooking.exception;

public class NoSuchElementException extends Exception{
    String msg;
    public NoSuchElementException(){

    }

    public NoSuchElementException(String msg) {
        super(msg);
        this.msg = msg;
    }
}