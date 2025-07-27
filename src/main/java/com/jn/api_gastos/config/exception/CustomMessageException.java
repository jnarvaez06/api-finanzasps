package com.jn.api_gastos.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CustomMessageException extends RuntimeException{
    private String message;

    public CustomMessageException(String message) {
        this.message = message;
    }
}
