package com.czareg.school.billing.school.dto;

import java.math.BigDecimal;

public record SchoolBillingDTO(

        int freeHours,
        int billableHours,
        BigDecimal hourPrice,
        BigDecimal totalBillingAmount,
        int totalHours
) {
}
