package com.somecompany.card.management.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyLimitRequest {
    @NonNull
    private BigDecimal dailyLimit;
}
