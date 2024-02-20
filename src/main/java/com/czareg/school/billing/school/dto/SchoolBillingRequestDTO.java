package com.czareg.school.billing.school.dto;

import java.util.Objects;

public record SchoolBillingRequestDTO(Long schoolId, int month) {

    public SchoolBillingRequestDTO {
        Objects.requireNonNull(schoolId, "SchoolId cannot be null");
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month value. Month must be between 1 and 12.");
        }
    }
}
