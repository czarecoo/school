package com.czareg.school.billing.common.dto;


import java.math.BigDecimal;
import java.util.List;

public record ChildBillingDTO(

        int freeHours,
        int billableHours,
        List<TimeSlotDTO> billableTimeslots,
        BigDecimal hourPrice,
        BigDecimal totalBillingAmount,
        int totalHours
) {
}
