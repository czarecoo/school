package com.czareg.school.billing.common.dto;

import java.time.LocalDateTime;

public record TimeSlotDTO(

        LocalDateTime start,
        LocalDateTime end
) {
}

