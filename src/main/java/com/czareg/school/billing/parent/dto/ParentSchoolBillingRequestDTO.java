package com.czareg.school.billing.parent.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ParentSchoolBillingRequestDTO(@NotNull @Min(0) Long parentId,
                                            @NotNull @Min(0) Long schoolId,
                                            @NotNull @Min(1) @Max(12) Integer month) {
}
