package com.weather.app.exceptions;

public class ErrorException extends RuntimeException{
    public ErrorException(String message) {
        super(message);
    }
}
