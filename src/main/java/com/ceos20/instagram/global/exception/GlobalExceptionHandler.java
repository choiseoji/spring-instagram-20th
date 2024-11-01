package com.ceos20.instagram.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(final NotFoundException e) {
        System.out.println(e.getMessage());
        System.out.println(e.getStatus());
        return ResponseEntity
                .status(e.getStatus())
                .body(ExceptionResponse.from(e));
    }
}
