package com.czareg.school.feature.billing.school.dto;

import com.czareg.school.feature.billing.common.dto.ChildDTO;
import com.czareg.school.feature.billing.common.dto.TotalBillingDTO;

import java.util.List;

public record SchoolBillingResponseDTO(

        TotalBillingDTO totalBilling,
        List<ChildDTO> children
) {
}
