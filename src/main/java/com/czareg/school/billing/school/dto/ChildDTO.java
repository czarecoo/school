package com.czareg.school.billing.school.dto;

public record ChildDTO(

        Long id,
        String firstName,
        String lastName,
        ParentDTO parent,
        ChildBillingDTO billing
) {
}
