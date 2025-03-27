package com.weather.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends Throwable {


    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<GlobalResponse> handleErrorException(final ErrorException ex) {
        GlobalResponse<String> response = new GlobalResponse<>();
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setDebugMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Internal Server Error", e.getMessage(), null));
    }
}
