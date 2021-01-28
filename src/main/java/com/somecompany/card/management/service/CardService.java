package com.somecompany.card.management.service;

import com.somecompany.card.management.dao.CardRepository;
import com.somecompany.card.management.entity.Card;
import com.somecompany.card.management.utils.EntityValidator;
import com.somecompany.card.management.utils.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class CardService {
    private CardRepository cardRepository;
    private UserService userService;

    public CardService(CardRepository cardRepository, UserService userService) {
        this.cardRepository = cardRepository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public List<Card> getCardsByUserId(long id) {
        userService.checkExist(id);
        log.info("Starting searching cards for card holder with id = {}", id);
        return cardRepository.getCardsByUserId(id);
    }

    @Transactional(readOnly = true)
    public Card getCardById(long id) {
        log.info("Starting searching card with id = {}", id);
        Card result = cardRepository.getById(id);
        if (result == null) {
            throw new EntityNotFoundException(Card.class.getSimpleName(), id);
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<Card> getAll() {
        log.info("Starting collecting all cards");
        return cardRepository.getAll();
    }

    @Transactional
    public Card activate(long id) {
        return changeStatus(id, true);
    }

    @Transactional
    public Card deactivate(long id) {
        return changeStatus(id, false);
    }

    @Transactional
    public Card updateDailyLimit(long id, BigDecimal dailyLimit) {
        Card savedCard = getCardById(id);
        log.info("Card with id = {} found and daily limit {} gonna be changed to {}", id, savedCard.getDailyLimit(), dailyLimit);
        savedCard = savedCard.toBuilder().dailyLimit(dailyLimit).build();
        EntityValidator.validate(savedCard);
        return cardRepository.update(savedCard);
    }

    private Card changeStatus(long id, boolean status) {
        Card savedCard = getCardById(id);
        log.info("Card with id = {} found and status %b gonna be changed to {}", id, savedCard.getIsActivated(), status);
        return cardRepository.update(savedCard.toBuilder().isActivated(status).build());
    }
}
