package com.guilherdrk.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderSummaryDTO(Long orderId,
                              LocalDateTime orderDate,
                              UUID userId,
                              BigDecimal total) {
}
