package com.czareg.school.billing.school.dto;

import com.czareg.school.billing.common.dto.TotalBillingDTO;

import java.util.List;

public record SchoolBillingResponseDTO(

        TotalBillingDTO billing,
        List<ChildDTO> children
) {
}
