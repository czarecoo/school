package com.czareg.school.billing.school.components;

import com.czareg.school.attendence.Attendance;
import com.czareg.school.attendence.AttendanceRepository;
import com.czareg.school.billing.school.dto.ChildBillingDTO;
import com.czareg.school.billing.school.dto.TimeSlotDTO;
import com.czareg.school.child.Child;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChildBillingPreparer {

    private final AttendanceRepository attendanceRepository;

    public ChildBillingDTO prepare(Child child, int month) {
        BigDecimal totalBillingAmount;
        List<TimeSlotDTO> billableTimeslots = new ArrayList<>();

        List<Attendance> attendances = attendanceRepository.findByChildIdAndMonth(child.getId(), month);

        BigDecimal hourPrice = child.getSchool().getHourPrice();

        int freeHours = 0;
        int billableHours = 0;

        for (Attendance attendance : attendances) {
            LocalDateTime entryDate = attendance.getEntryDate();
            LocalDateTime exitDate = attendance.getExitDate();

            List<TimeSlotDTO> hourlySlots = splitTimeIntoHourlySlots(entryDate, exitDate);

            for (TimeSlotDTO slot : hourlySlots) {
                if (isFreeSlot(slot)) {
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

    private List<TimeSlotDTO> splitTimeIntoHourlySlots(LocalDateTime entryDate, LocalDateTime exitDate) {
        List<TimeSlotDTO> hourlySlots = new ArrayList<>();

        LocalDateTime currentHour = entryDate.withMinute(0).withSecond(0).withNano(0);
        while (currentHour.isBefore(exitDate)) {
            LocalDateTime nextHour = currentHour.plusHours(1);
            LocalDateTime slotEntry = currentHour.isBefore(entryDate) ? entryDate : currentHour;
            LocalDateTime slotExit = nextHour.isAfter(exitDate) ? exitDate : nextHour;
            hourlySlots.add(new TimeSlotDTO(slotEntry, slotExit));
            currentHour = nextHour;
        }

        return hourlySlots;
    }

    private boolean isFreeSlot(TimeSlotDTO slot) {
        LocalDateTime startFreeTime = slot.start().withHour(7).withMinute(0);
        LocalDateTime endFreeTime = slot.end().withHour(12).withMinute(0);

        return (slot.start().isAfter(startFreeTime) || slot.start().isEqual(startFreeTime)) &&
                (slot.end().isBefore(endFreeTime) || slot.end().isEqual(endFreeTime));
    }
}