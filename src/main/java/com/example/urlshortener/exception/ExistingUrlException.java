package com.example.urlshortener.exception;

public class ExistingUrlException extends RuntimeException {
    public ExistingUrlException(String message) {
        super(message);
    }
}