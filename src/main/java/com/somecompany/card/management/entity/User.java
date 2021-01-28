package com.somecompany.card.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static com.somecompany.card.management.utils.ErrorMessageUtils.ERROR_MESSAGE_WHEN_NON_NULL_PROPERTY_IS_NULL;
import static com.somecompany.card.management.utils.ErrorMessageUtils.ERROR_MESSAGE_WHEN_VALUE_IS_TOO_LONG_OR_EMPTY;

@Getter
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_name")
    @Size(min = 1, max = 100, message = ERROR_MESSAGE_WHEN_VALUE_IS_TOO_LONG_OR_EMPTY)
    private String userName;

    @Column(name = "user_address")
    @Size(min = 1, max = 255, message = ERROR_MESSAGE_WHEN_NON_NULL_PROPERTY_IS_NULL)
    private String userAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "cardHolder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Card> cards = new HashSet<>();
}
