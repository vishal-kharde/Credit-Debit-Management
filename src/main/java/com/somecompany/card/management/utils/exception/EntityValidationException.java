package com.somecompany.card.management.utils.exception;

public class EntityValidationException extends RuntimeException {
    private String message;

    public EntityValidationException(String message) {
        super(message);
    }
}
