package com.example.trainbooking.exception;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
public class CustomerIdNotFoundException extends RuntimeException{
    private final int status;

    public CustomerIdNotFoundException(String message, int status) {
        super(message);
        this.status = status;
    }
}
