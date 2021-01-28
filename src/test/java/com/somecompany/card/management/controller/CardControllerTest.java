package com.somecompany.card.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.somecompany.card.management.controller.request.DailyLimitRequest;
import com.somecompany.card.management.entity.Card;
import com.somecompany.card.management.service.CardService;
import com.somecompany.card.management.utils.ErrorMessageUtils;
import com.somecompany.card.management.utils.exception.EntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CardController.class)
public class CardControllerTest {

    @MockBean
    private CardService service;

    @Autowired
    private MockMvc mvc;
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldGetAll() throws Exception {
        Mockito.when(service.getAll())
                .thenReturn(Arrays.asList(
                        Card.builder().id(11).build(),
                        Card.builder().id(12).build(),
                        Card.builder().id(13).build()));

        mvc.perform(MockMvcRequestBuilders
                .get("/cards")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));
    }

    @Test
    public void shouldGetEmptyList() throws Exception {
        Mockito.when(service.getAll()).thenReturn(Collections.emptyList());

        mvc.perform(MockMvcRequestBuilders
                .get("/cards")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetById() throws Exception {
        Mockito.when(service.getCardById(Mockito.anyLong()))
                .thenReturn(Card.builder().id(11).build());

        mvc.perform(MockMvcRequestBuilders
                .get("/cards/{id}", 11)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(11));
    }

    @Test
    public void shouldNotGetByNonExistingId() throws Exception {
        Mockito.doThrow(new EntityNotFoundException(ErrorMessageUtils.ENTITY_NOT_FOUND_EXCEPTION, 15))
                .when(service).getCardById(Mockito.anyLong());

        mvc.perform(MockMvcRequestBuilders
                .get("/cards/{id}", 15)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetByUserId() throws Exception {
        Mockito.when(service.getCardsByUserId(Mockito.anyLong()))
                .thenReturn(Arrays.asList(Card.builder().id(11).build(), Card.builder().id(12).build()));

        mvc.perform(MockMvcRequestBuilders
                .get("/users/{id}/cards", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldGetEmptyListByUserId() throws Exception {
        Mockito.when(service.getCardsByUserId(Mockito.anyLong()))
                .thenReturn(Collections.emptyList());

        mvc.perform(MockMvcRequestBuilders
                .get("/users/{id}/cards", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldNotGetByNonExistingUserId() throws Exception {
        Mockito.doThrow(new EntityNotFoundException(ErrorMessageUtils.ENTITY_NOT_FOUND_EXCEPTION, 15))
                .when(service).getCardsByUserId(Mockito.anyLong());

        mvc.perform(MockMvcRequestBuilders
                .get("/users/{id}/cards", 15)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetActivateById() throws Exception {
        long id = 11;
        Mockito.when(service.activate(Mockito.anyLong()))
                .thenReturn(Card.builder().id(id).isActivated(true).build());

        mvc.perform(MockMvcRequestBuilders
                .patch("/cards/{id}/activate", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    public void shouldNotActivateByNonExistingId() throws Exception {
        long id = 111;
        Mockito.doThrow(new EntityNotFoundException(ErrorMessageUtils.ENTITY_NOT_FOUND_EXCEPTION, id))
                .when(service).activate(Mockito.eq(id));

        mvc.perform(MockMvcRequestBuilders
                .patch("/cards/{id}/activate", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetDeactivateById() throws Exception {
        long id = 14;
        Mockito.when(service.deactivate(Mockito.anyLong()))
                .thenReturn(Card.builder().id(id).isActivated(true).build());

        mvc.perform(MockMvcRequestBuilders
                .patch("/cards/{id}/deactivate", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    public void shouldNotDeactivateByNonExistingId() throws Exception {
        long id = 17;
        Mockito.doThrow(new EntityNotFoundException(ErrorMessageUtils.ENTITY_NOT_FOUND_EXCEPTION, id))
                .when(service).deactivate(Mockito.anyLong());

        mvc.perform(MockMvcRequestBuilders
                .patch("/cards/{id}/deactivate", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateDailyLimit() throws Exception {
        long id = 2;
        BigDecimal newDailyLimit = new BigDecimal(665.7676);
        DailyLimitRequest request = new DailyLimitRequest(newDailyLimit);

        Mockito.when(service.updateDailyLimit(Mockito.anyLong(), Mockito.any()))
                .thenReturn(Card.builder().id(2)
                        .isActivated(true)
                        .dailyLimit(newDailyLimit)
                        .balance(newDailyLimit)
                        .cardNumber("371956467105023")
                        .build());

        mvc.perform(MockMvcRequestBuilders
                .patch("/cards/{id}", id)
                .content(mapper.writeValueAsString(request))
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenUpdateDailyLimitByNonExistingId() throws Exception {
        long id = 22;
        BigDecimal newDailyLimit = new BigDecimal(665.7676);
        DailyLimitRequest request = new DailyLimitRequest(newDailyLimit);

        Mockito.doThrow(new EntityNotFoundException(ErrorMessageUtils.ENTITY_NOT_FOUND_EXCEPTION, id))
                .when(service).updateDailyLimit(Mockito.anyLong(), Mockito.any());

        mvc.perform(MockMvcRequestBuilders
                .patch("/cards/{id}", id)
                .content(mapper.writeValueAsString(request))
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
