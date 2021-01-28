package com.somecompany.card.management.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.somecompany.card.management.utils.ErrorMessageUtils.*;

@Entity
@Table(name = "card")
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "daily_limit")
    @NotNull(message = ERROR_MESSAGE_WHEN_NON_NULL_PROPERTY_IS_NULL)
    @Min(value = 0, message = ERROR_MESSAGE_WHEN_VALUE_CANNOT_BE_NEGATIVE)
    private BigDecimal dailyLimit;

    @Column(name = "balance")
    @NotNull(message = ERROR_MESSAGE_WHEN_NON_NULL_PROPERTY_IS_NULL)
    private BigDecimal balance;

    @Column(name = "card_number")
    @Pattern(regexp = "^\\d{16}$", message = ERROR_MESSAGE_WHEN_CARD_NUMBER_IS_INCORRECT)
    @NotNull(message = ERROR_MESSAGE_WHEN_NON_NULL_PROPERTY_IS_NULL)
    private String cardNumber;

    @Column(name = "expiration_date")
    @NotNull(message = ERROR_MESSAGE_WHEN_NON_NULL_PROPERTY_IS_NULL)
    @Future(message = ERROR_MESSAGE_WHEN_EXPIRATION_DATE_INCORRECT)
    private LocalDate expirationDate;

    @Column(name = "is_activated")
    @NotNull(message = ERROR_MESSAGE_WHEN_NON_NULL_PROPERTY_IS_NULL)
    private Boolean isActivated;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_holder_id")
    private User cardHolder;
}
