package com.example.notificationserver.controllers;

import com.example.notificationserver.models.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler({SignatureException.class,
            MalformedJwtException.class,
            ExpiredJwtException.class,
            UnsupportedJwtException.class,
            IllegalArgumentException.class})
    @ResponseStatus(UNAUTHORIZED)
    public ErrorResponse handleValidationException(Exception ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .status(UNAUTHORIZED)
                .timestamp(now())
                .build();
    }
}