package com.czareg.school.billing.parent.dto;

import com.czareg.school.billing.common.dto.ChildBillingDTO;

public record ChildDTO(

        Long id,
        String firstName,
        String lastName,
        ChildBillingDTO billing
) {
}
