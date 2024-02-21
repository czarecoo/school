package com.czareg.school.feature.billing.common.dto;

import java.time.LocalDateTime;

public record TimeSlotDTO(

        LocalDateTime start,
        LocalDateTime end
) {
}

