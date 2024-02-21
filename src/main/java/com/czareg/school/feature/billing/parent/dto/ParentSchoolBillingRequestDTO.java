package com.czareg.school.feature.billing.parent.dto;

import com.czareg.school.config.validation.ParentExists;
import com.czareg.school.config.validation.SchoolExists;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ParentSchoolBillingRequestDTO(@ParentExists Long parentId,
                                            @SchoolExists Long schoolId,
                                            @NotNull @Min(1) @Max(12) Integer month) {
}
