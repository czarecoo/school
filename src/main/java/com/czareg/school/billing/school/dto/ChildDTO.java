package com.czareg.school.billing.school.dto;

import com.czareg.school.billing.common.dto.ChildBillingDTO;

public record ChildDTO(

        Long id,
        String firstName,
        String lastName,
        ParentDTO parent,
        ChildBillingDTO billing
) {
}
