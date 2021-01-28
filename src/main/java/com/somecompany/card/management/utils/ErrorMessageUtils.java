package com.somecompany.card.management.utils;

public class ErrorMessageUtils {
    public static final String ERROR_MESSAGE_WHEN_EXPIRATION_DATE_INCORRECT = "Expiration date passed";
    public static final String ERROR_MESSAGE_WHEN_NON_NULL_PROPERTY_IS_NULL = "Property can not be empty";
    public static final String ERROR_MESSAGE_WHEN_VALUE_IS_TOO_LONG_OR_EMPTY = "Value can not be empty or too long";
    public static final String ERROR_MESSAGE_WHEN_VALUE_CANNOT_BE_NEGATIVE = "Value can not be negative";
    public static final String ERROR_MESSAGE_WHEN_CARD_NUMBER_IS_INCORRECT = "Card number must include only 16 numbers";

    public static final String ENTITY_NOT_FOUND_EXCEPTION = "There is no %s with id = %d";
}
