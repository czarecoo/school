package com.czareg.school.billing.school.dto;

import java.util.List;

public record SchoolBillingResponseDTO(

        SchoolBillingDTO billing,
        List<ChildDTO> children
) {
}
