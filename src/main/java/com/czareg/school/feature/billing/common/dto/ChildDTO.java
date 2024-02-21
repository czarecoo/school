package com.czareg.school.feature.billing.common.dto;

public record ChildDTO(

        Long id,
        String firstName,
        String lastName,
        ParentDTO parent,
        ChildBillingDTO billing
) {
}
