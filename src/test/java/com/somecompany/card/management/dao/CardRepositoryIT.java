package com.somecompany.card.management.dao;

import com.somecompany.card.management.entity.Card;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("com.somecompany.card.management")
@Rollback
public class CardRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CardRepository cardRepository;

    @Rule
    public JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Test
    public void shouldGetCardById() {
        long id = 10;
        Card card = cardRepository.getById(id);

        softly.assertThat(card).isNotNull();
        softly.assertThat(card.getId()).isEqualTo(id);
        softly.assertThat(card.getCardHolder()).isNotNull();
        softly.assertThat(card.getDailyLimit().intValue()).isEqualTo(5362);
        softly.assertThat(card.getBalance().doubleValue()).isEqualTo(83487630237.78768);
        softly.assertThat(card.getExpirationDate()).isEqualTo(LocalDate.of(2030, 12, 1));
        softly.assertThat(card.getCardNumber()).isEqualTo("4024007143743572");
        softly.assertThat(card.getIsActivated()).isTrue();
    }

    @Test
    public void shouldGetNullByNonExistingId() {
        long id = 90;
        Card card = cardRepository.getById(id);
        softly.assertThat(card).isNull();
    }

    @Test
    public void shouldGetAllCards() {
        List<Card> cards = cardRepository.getAll();

        softly.assertThat(cards).hasSize(13);
    }

    @Test
    public void getCardsByUserId() {
        long id = 2;
        List<Card> userCards = cardRepository.getCardsByUserId(id);

        softly.assertThat(userCards).hasSize(3);
    }

    @Test
    public void shouldGetEmptyListByNonExistingUSerId() {
        long id = 90;
        List<Card> userCards = cardRepository.getCardsByUserId(id);

        softly.assertThat(userCards).isEmpty();
    }

    @Test
    public void shouldUpdateExistingCard() {
        Card savedCard = cardRepository.getById(1);
        BigDecimal newDailyLimit = savedCard.getDailyLimit().add(new BigDecimal(100));

        Card updatedCard = cardRepository.update(savedCard.toBuilder().dailyLimit(newDailyLimit).build());
        savedCard = cardRepository.getById(1);
        softly.assertThat(savedCard.getDailyLimit()).isEqualTo(newDailyLimit);
        softly.assertThat(updatedCard.getDailyLimit()).isEqualTo(savedCard.getDailyLimit());
    }

}
