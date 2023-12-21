package com.altimetrik.trainroute.exception;

public class NoSuchElementException extends Exception{
    String msg;
    public NoSuchElementException(){

    }
    public NoSuchElementException(String msg) {
        super(msg);
        this.msg = msg;
    }
}