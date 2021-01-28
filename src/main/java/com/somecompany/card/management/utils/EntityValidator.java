package com.somecompany.card.management.utils;

import com.somecompany.card.management.utils.exception.EntityValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class EntityValidator {
    private EntityValidator() {

    }

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> void validate(T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            StringBuffer commonErrorMessage = new StringBuffer();
            violations.iterator().forEachRemaining(a -> commonErrorMessage.append(a.getMessage()).append(". "));
            throw new EntityValidationException(commonErrorMessage.toString());
        }
    }

}
