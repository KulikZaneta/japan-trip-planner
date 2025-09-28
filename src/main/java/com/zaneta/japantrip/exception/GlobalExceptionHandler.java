package com.zaneta.japantrip.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<Map<String, String>> handleInvalidField(InvalidFieldException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

}

