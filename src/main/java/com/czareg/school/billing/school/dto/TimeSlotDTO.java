package com.czareg.school.billing.school.dto;

import java.time.LocalDateTime;

public record TimeSlotDTO(

        LocalDateTime start,
        LocalDateTime end
) {
}

