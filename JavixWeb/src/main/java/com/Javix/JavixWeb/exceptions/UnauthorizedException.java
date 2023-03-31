package com.Javix.JavixWeb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
    private String message;

    public UnauthorizedException(String message) {
        super(message);
        this.message = message;
    }
}
