package org.example.booking.mvc.exceptions;

import lombok.Data;

@Data
public class MoneyNotEnoughException extends RuntimeException {
    private Throwable cause;
    private String message;

    public MoneyNotEnoughException() {
    }

    public MoneyNotEnoughException(String message) {
        this.message = message;
    }

    public MoneyNotEnoughException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }
}
