package com.czareg.school.feature.billing.common.dto;

import java.math.BigDecimal;

public record TotalBillingDTO(

        int freeHours,
        int billableHours,
        BigDecimal hourPrice,
        BigDecimal totalBillingAmount,
        int totalHours
) {
}
