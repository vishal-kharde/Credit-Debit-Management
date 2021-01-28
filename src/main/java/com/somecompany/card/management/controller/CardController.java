package com.somecompany.card.management.controller;

import com.somecompany.card.management.controller.request.DailyLimitRequest;
import com.somecompany.card.management.entity.Card;
import com.somecompany.card.management.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class CardController {

    private CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("users/{userId}/cards")
    public ResponseEntity getCardsByUserId(@PathVariable("userId") long userId) {
        List<Card> cards = cardService.getCardsByUserId(userId);
        return cards.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cards);
    }

    @GetMapping("cards/{id}")
    public ResponseEntity getCardsById(@PathVariable("id") long id) {
        Card card = cardService.getCardById(id);
        return ResponseEntity.ok(card);
    }

    @GetMapping("cards")
    public ResponseEntity getCardsById() {
        List<Card> cards = cardService.getAll();
        return cards.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cards);
    }

    @PatchMapping("cards/{id}/activate")
    public ResponseEntity activate(@PathVariable("id") long id) {
        return ResponseEntity.ok(cardService.activate(id));
    }

    @PatchMapping("cards/{id}/deactivate")
    public ResponseEntity deactivate(@PathVariable("id") long id) {
        return ResponseEntity.ok(cardService.deactivate(id));
    }

    @PatchMapping("cards/{id}")
    public ResponseEntity updateDailyLimit(@PathVariable("id") long id, @RequestBody @Valid DailyLimitRequest dailyLimitRequest) {
        return ResponseEntity.ok(cardService.updateDailyLimit(id, dailyLimitRequest.getDailyLimit()));
    }
}
