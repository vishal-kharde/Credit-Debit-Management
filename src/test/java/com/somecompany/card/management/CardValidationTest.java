package com.somecompany.card.management;


import com.somecompany.card.management.entity.Card;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static com.somecompany.card.management.utils.ErrorMessageUtils.*;

public class CardValidationTest {
    private static Validator validator;
    private static Card validCard;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        validCard = Card.builder()
                .id(1)
                .cardNumber("4024007143743546")
                .balance(new BigDecimal(345))
                .dailyLimit(new BigDecimal(3))
                .expirationDate(LocalDate.of(2019, 12, 1))
                .isActivated(true)
                .build();
    }

    @Test
    public void shouldCreateValidCard() {
        Set<ConstraintViolation<Card>> violations = validator.validate(validCard);

        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void shouldNotCreateCardWithNumberWithinIncorrectSymbols() {
        Card card = validCard.toBuilder().cardNumber("49293238910534QQ").build();
        Set<ConstraintViolation<Card>> violations = validator.validate(card);

        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(ERROR_MESSAGE_WHEN_CARD_NUMBER_IS_INCORRECT,
                violations.iterator().next().getMessage());
    }

    @Test
    public void shouldNotCreateCardWithShortNumber() {
        Card card = validCard.toBuilder()
                .cardNumber("40240071437435") // 14 symbols
                .build();
        Set<ConstraintViolation<Card>> violations = validator.validate(card);

        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(ERROR_MESSAGE_WHEN_CARD_NUMBER_IS_INCORRECT,
                violations.iterator().next().getMessage());
    }

    @Test
    public void shouldNotCreateCardWithLongNumber() {
        Card card = validCard.toBuilder()
                .cardNumber("40240071437435345325435325325")
                .build();
        Set<ConstraintViolation<Card>> violations = validator.validate(card);

        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(ERROR_MESSAGE_WHEN_CARD_NUMBER_IS_INCORRECT,
                violations.iterator().next().getMessage());
    }

    @Test
    public void shouldNotCreateCardWithNullNumber() {
        Card card = validCard.toBuilder()
                .cardNumber(null)
                .build();
        Set<ConstraintViolation<Card>> violations = validator.validate(card);

        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(ERROR_MESSAGE_WHEN_NON_NULL_PROPERTY_IS_NULL,
                violations.iterator().next().getMessage());
    }

    @Test
    public void shouldNotCreateCardWithIncorrectExpirationDate() {
        Card card = validCard.toBuilder()
                .expirationDate(LocalDate.of(2018, 3, 1))
                .build();
        Set<ConstraintViolation<Card>> violations = validator.validate(card);

        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(ERROR_MESSAGE_WHEN_EXPIRATION_DATE_INCORRECT,
                violations.iterator().next().getMessage());
    }

    @Test
    public void shouldNotCreateCardWithNullExpiration() {
        Card card = validCard.toBuilder()
                .expirationDate(null)
                .build();
        Set<ConstraintViolation<Card>> violations = validator.validate(card);

        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(ERROR_MESSAGE_WHEN_NON_NULL_PROPERTY_IS_NULL,
                violations.iterator().next().getMessage());
    }

    @Test
    public void shouldNotCreateCardWithNegativeDailyLimit() {
        Card card = validCard.toBuilder()
                .dailyLimit(new BigDecimal(-3))
                .build();
        Set<ConstraintViolation<Card>> violations = validator.validate(card);

        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(ERROR_MESSAGE_WHEN_VALUE_CANNOT_BE_NEGATIVE,
                violations.iterator().next().getMessage());
    }

    @Test
    public void shouldNotCreateCardWithNullDailyLimit() {
        Card card = validCard.toBuilder()
                .dailyLimit(null)
                .build();
        Set<ConstraintViolation<Card>> violations = validator.validate(card);

        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(ERROR_MESSAGE_WHEN_NON_NULL_PROPERTY_IS_NULL,
                violations.iterator().next().getMessage());
    }

    @Test
    public void shouldNotCreateCardWithNullBalance() {
        Card card = validCard.toBuilder()
                .balance(null)
                .build();
        Set<ConstraintViolation<Card>> violations = validator.validate(card);

        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(ERROR_MESSAGE_WHEN_NON_NULL_PROPERTY_IS_NULL,
                violations.iterator().next().getMessage());
    }

    @Test
    public void shouldNotCreateCardWithNullStatus() {
        Card card = validCard.toBuilder()
                .isActivated(null)
                .build();
        Set<ConstraintViolation<Card>> violations = validator.validate(card);

        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(ERROR_MESSAGE_WHEN_NON_NULL_PROPERTY_IS_NULL,
                violations.iterator().next().getMessage());
    }
}
