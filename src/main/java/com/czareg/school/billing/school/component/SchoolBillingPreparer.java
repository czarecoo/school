package com.czareg.school.billing.school.component;

import com.czareg.school.billing.common.dto.ChildBillingDTO;
import com.czareg.school.billing.common.dto.TotalBillingDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SchoolBillingPreparer {

    public TotalBillingDTO prepare(List<ChildBillingDTO> childBillingDTOS) {
        int totalFreeHours = 0;
        int totalBillableHours = 0;
        BigDecimal totalBillingAmount = BigDecimal.ZERO;

        for (ChildBillingDTO childBillingDTO : childBillingDTOS) {
            totalFreeHours += childBillingDTO.freeHours();
            totalBillableHours += childBillingDTO.billableHours();
            totalBillingAmount = totalBillingAmount.add(childBillingDTO.totalBillingAmount());
        }

        BigDecimal hourPrice = childBillingDTOS.isEmpty() ? BigDecimal.ZERO : childBillingDTOS.get(0).hourPrice();

        int totalHours = totalFreeHours + totalBillableHours;

        return new TotalBillingDTO(totalFreeHours, totalBillableHours, hourPrice, totalBillingAmount, totalHours);
    }
}
