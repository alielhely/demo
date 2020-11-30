package com.example.demo.exception;

public class NoSufficientAmountFoundToNewAccountException extends RuntimeException {

    public NoSufficientAmountFoundToNewAccountException(String message) {
        super(message);
    }
}
