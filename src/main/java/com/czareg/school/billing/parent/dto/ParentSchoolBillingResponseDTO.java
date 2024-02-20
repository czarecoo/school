package com.czareg.school.billing.parent.dto;

import com.czareg.school.billing.common.dto.TotalBillingDTO;

import java.util.List;

public record ParentSchoolBillingResponseDTO(

        TotalBillingDTO billing,
        List<ChildDTO> children
) {
}
