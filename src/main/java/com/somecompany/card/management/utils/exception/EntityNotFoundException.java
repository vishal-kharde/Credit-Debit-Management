package com.somecompany.card.management.utils.exception;

import com.somecompany.card.management.utils.ErrorMessageUtils;

public class EntityNotFoundException extends RuntimeException {
    private static final String message = ErrorMessageUtils.ENTITY_NOT_FOUND_EXCEPTION;

    public EntityNotFoundException(String className, long id) {
        super(String.format(message, className, id));
    }
}
