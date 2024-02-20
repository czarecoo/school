package com.czareg.school.billing.school.components;

import com.czareg.school.billing.school.dto.ChildDTO;
import com.czareg.school.billing.school.dto.SchoolBillingDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SchoolBillingPreparer {

    public SchoolBillingDTO prepare(List<ChildDTO> children) {
        int totalFreeHours = 0;
        int totalBillableHours = 0;
        BigDecimal totalBillingAmount = BigDecimal.ZERO;

        for (ChildDTO child : children) {
            totalFreeHours += child.billing().freeHours();
            totalBillableHours += child.billing().billableHours();
            totalBillingAmount = totalBillingAmount.add(child.billing().totalBillingAmount());
        }

        BigDecimal hourPrice = children.isEmpty() ? BigDecimal.ZERO : children.get(0).billing().hourPrice();

        int totalHours = totalFreeHours + totalBillableHours;

        return new SchoolBillingDTO(totalFreeHours, totalBillableHours, hourPrice, totalBillingAmount, totalHours);
    }
}
