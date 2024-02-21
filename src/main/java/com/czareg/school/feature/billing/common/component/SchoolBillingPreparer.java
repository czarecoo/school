package com.czareg.school.feature.billing.common.component;

import com.czareg.school.feature.billing.common.dto.ChildBillingDTO;
import com.czareg.school.feature.billing.common.dto.ChildDTO;
import com.czareg.school.feature.billing.common.dto.TotalBillingDTO;
import com.czareg.school.feature.school.School;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SchoolBillingPreparer {

    public TotalBillingDTO prepare(List<ChildDTO> childDTOList, School school) {
        int totalFreeHours = 0;
        int totalBillableHours = 0;
        BigDecimal totalBillingAmount = BigDecimal.ZERO;

        for (ChildDTO childDTO : childDTOList) {
            ChildBillingDTO childBillingDTO = childDTO.billing();
            totalFreeHours += childBillingDTO.freeHours();
            totalBillableHours += childBillingDTO.billableHours();
            totalBillingAmount = totalBillingAmount.add(childBillingDTO.totalBillingAmount());
        }

        BigDecimal hourPrice = school.getHourPrice();

        int totalHours = totalFreeHours + totalBillableHours;

        return new TotalBillingDTO(totalFreeHours, totalBillableHours, hourPrice, totalBillingAmount, totalHours);
    }
}
