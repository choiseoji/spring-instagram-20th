package com.ceos20.instagram.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionResponse {

    private final HttpStatus status;

    private final String message;

    public ExceptionResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ExceptionResponse from(HttpStatus status, String message) {
        return new ExceptionResponse(status, message);
    }
}
