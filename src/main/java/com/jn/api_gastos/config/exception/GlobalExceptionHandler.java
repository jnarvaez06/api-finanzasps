package com.jn.api_gastos.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodNotValidException(MethodArgumentNotValidException exception, WebRequest webRequest) {
        Map<String, String> mapErrors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((objectError -> {
            String key = ((FieldError) objectError).getField();
            String value = objectError.getDefaultMessage();
            mapErrors.put(key, value);
        }));

        ApiResponseErrors apiResponseErrors = new ApiResponseErrors(mapErrors.toString(), webRequest.getDescription(false));

        return new ResponseEntity<>(apiResponseErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "status", 404,
                "message", ex.getMessage()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "status", 500,
                "message", "Internal Server Error",
                "error", ex.getMessage()
        ));
    }
}
