package com.czareg.school.feature.billing.common.component;

import com.czareg.school.feature.attendence.Attendance;
import com.czareg.school.feature.billing.common.dto.ChildBillingDTO;
import com.czareg.school.feature.billing.common.dto.TimeSlotDTO;
import com.czareg.school.feature.school.School;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChildBillingPreparer {

    private final TimeSlotManager timeSlotManager;

    public ChildBillingDTO prepare(@NonNull List<Attendance> attendanceList, @NonNull School school) {
        BigDecimal totalBillingAmount;
        List<TimeSlotDTO> billableTimeslots = new ArrayList<>();

        BigDecimal hourPrice = school.getHourPrice();

        int freeHours = 0;
        int billableHours = 0;

        for (Attendance attendance : attendanceList) {
            LocalDateTime entryDate = attendance.getEntryDate();
            LocalDateTime exitDate = attendance.getExitDate();

            List<TimeSlotDTO> hourlySlots = timeSlotManager.splitTimeIntoHourlySlots(entryDate, exitDate);

            for (TimeSlotDTO slot : hourlySlots) {
                if (timeSlotManager.isTimeSlotFree(slot)) {
                    freeHours++;
                } else {
                    billableTimeslots.add(slot);
                    billableHours++;
                }
            }
        }
        totalBillingAmount = hourPrice.multiply(BigDecimal.valueOf(billableHours));
        return new ChildBillingDTO(freeHours, billableHours, billableTimeslots, hourPrice, totalBillingAmount, freeHours + billableHours);
    }
}