package com.czareg.school.feature.billing.parent.dto;

import com.czareg.school.feature.billing.common.dto.ChildDTO;
import com.czareg.school.feature.billing.common.dto.TotalBillingDTO;

import java.util.List;

public record ParentSchoolBillingResponseDTO(

        TotalBillingDTO totalBilling,
        List<ChildDTO> children
) {
}
