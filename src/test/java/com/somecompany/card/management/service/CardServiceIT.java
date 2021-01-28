package com.somecompany.card.management.service;

import com.somecompany.card.management.entity.Card;
import com.somecompany.card.management.utils.ErrorMessageUtils;
import com.somecompany.card.management.utils.exception.EntityValidationException;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
public class CardServiceIT {

    @Autowired
    private CardService cardService;

    @Rule
    public JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Test
    public void shouldGetCardsByUserId() {
        long userId = 2;
        List<Card> userCards = cardService.getCardsByUserId(userId);
        softly.assertThat(userCards).hasSize(3);
    }

    @Test
    public void shouldGetEmptyListByNonExistingUserId() {
        long userId = 8;
        List<Card> userCards = cardService.getCardsByUserId(userId);
        softly.assertThat(userCards.isEmpty()).isTrue();
    }

    @Test
    public void shouldGetAll13Cards() {
        List<Card> cards = cardService.getAll();
        softly.assertThat(cards).hasSize(13);
    }

    @Test
    public void shouldGetCardById() {
        long id = 2;
        Card card = cardService.getCardById(id);

        softly.assertThat(card).isNotNull();
        softly.assertThat(card.getId()).isEqualTo(id);
        softly.assertThat(card.getCardHolder()).isNotNull();
        softly.assertThat(card.getDailyLimit().intValue()).isEqualTo(789);
        softly.assertThat(card.getBalance().intValue()).isEqualTo(4386);
        softly.assertThat(card.getExpirationDate()).isEqualTo(LocalDate.of(2023, 9, 1));
        softly.assertThat(card.getCardNumber()).isEqualTo("5265227000100063");
        softly.assertThat(card.getIsActivated()).isTrue();
    }

    @Test
    public void shouldThrowExceptionByNonExistingCardId() {
        long id = 80;
        try {
            cardService.getCardById(id);
            fail("Non existing card should not be found");
        } catch (EntityValidationException e) {
            softly.assertThat(e.getMessage()).isEqualTo(String.format(ErrorMessageUtils.ENTITY_NOT_FOUND_EXCEPTION, id));
        }
    }

    @Test
    public void shouldActivateDeactivatedCard() {
        long id = 1;
        Card updatedCard = cardService.activate(id);
        Card savedCard = cardService.getCardById(id);

        softly.assertThat(updatedCard.getIsActivated()).isTrue();
        softly.assertThat(savedCard.getIsActivated()).isTrue();
    }

    @Test
    public void shouldActivateActivatedCard() {
        long id = 2;
        Card updatedCard = cardService.activate(id);
        Card savedCard = cardService.getCardById(id);

        softly.assertThat(updatedCard.getIsActivated()).isTrue();
        softly.assertThat(savedCard.getIsActivated()).isTrue();
    }

    @Test
    public void shouldDeactivateActivatedCard() {
        long id = 2;
        Card updatedCard = cardService.deactivate(id);
        Card savedCard = cardService.getCardById(id);

        softly.assertThat(updatedCard.getIsActivated()).isFalse();
        softly.assertThat(savedCard.getIsActivated()).isFalse();
    }

    @Test
    public void shouldDeactivateDeactivatedCard() {
        long id = 1;
        Card updatedCard = cardService.deactivate(id);
        Card savedCard = cardService.getCardById(id);

        softly.assertThat(updatedCard.getIsActivated()).isFalse();
        softly.assertThat(savedCard.getIsActivated()).isFalse();
    }

    @Test
    public void shouldUpdateDailyLimit() {
        long id = 1;
        Card savedCard = cardService.getCardById(id);
        BigDecimal newDailyLimit = savedCard.getDailyLimit().add(new BigDecimal(100));

        Card updatedCard = cardService.updateDailyLimit(id, newDailyLimit);
        savedCard = cardService.getCardById(id);

        softly.assertThat(updatedCard.getDailyLimit()).isEqualTo(newDailyLimit);
        softly.assertThat(updatedCard.getDailyLimit()).isEqualTo(savedCard.getDailyLimit());
    }

    @Test
    public void shouldThrowValidationErrorWhenUpdateDailyLimitToNegative() {
        long id = 2;
        BigDecimal newDailyLimit = new BigDecimal(-100);

        try {
            cardService.updateDailyLimit(id, newDailyLimit);
            fail("Card should not be updated with negative daily limit");
        } catch (EntityValidationException e) {
            softly.assertThat(e.getMessage()).isEqualTo(ErrorMessageUtils.ERROR_MESSAGE_WHEN_VALUE_CANNOT_BE_NEGATIVE);
        }
    }
}
