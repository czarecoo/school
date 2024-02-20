package com.czareg.school.billing.common.dto;

import java.math.BigDecimal;

public record TotalBillingDTO(

        int freeHours,
        int billableHours,
        BigDecimal hourPrice,
        BigDecimal totalBillingAmount,
        int totalHours
) {
}
