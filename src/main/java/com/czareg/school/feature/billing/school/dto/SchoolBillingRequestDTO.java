package com.czareg.school.feature.billing.school.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

public record SchoolBillingRequestDTO(@NonNull @Min(0) Long schoolId,
                                      @NotNull @Min(1) @Max(12) Integer month) {
}
