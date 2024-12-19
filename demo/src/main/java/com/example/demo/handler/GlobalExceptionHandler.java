package com.example.demo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // DTO for generic error
    public static class ErrorDto {
        private String message;
        private LocalDateTime timestamp;

        public ErrorDto(String message) {
            this.message = message;
            this.timestamp = LocalDateTime.now();
        }

        public String getMessage() {
            return message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }

    // DTO for invalid entity error
    public static class InvalidEntityErrorDto {
        private Map<String, String> errors;
        private LocalDateTime timestamp;

        public InvalidEntityErrorDto(Map<String, String> errors) {
            this.errors = errors;
            this.timestamp = LocalDateTime.now();
        }

        public Map<String, String> getErrors() {
            return errors;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }

    // Handles EntityNotFoundException
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ErrorDto(ex.getMessage());
    }

    // Custom exceptions for bad requests
    public static class EntityToCreateHasAnIdException extends RuntimeException {
        public EntityToCreateHasAnIdException(String message) {
            super(message);
        }
    }

    public static class EntityToCreateHasNoIdException extends RuntimeException {
        public EntityToCreateHasNoIdException(String message) {
            super(message);
        }
    }

    // Handles EntityToCreateHasAnIdException and EntityToCreateHasNoIdException
    @ExceptionHandler({EntityToCreateHasAnIdException.class, EntityToCreateHasNoIdException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleBadRequestExceptions(RuntimeException ex) {
        return new ErrorDto(ex.getMessage());
    }

    // Handles MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public InvalidEntityErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new InvalidEntityErrorDto(errors);
    }
}
