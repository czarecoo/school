package com.czareg.school.feature.billing.common.component;

import com.czareg.school.config.FreeTimeSlotConfig;
import com.czareg.school.feature.billing.common.dto.TimeSlotDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TimeSlotManager {

    private final FreeTimeSlotConfig freeTimeSlotConfig;

    public List<TimeSlotDTO> splitTimeIntoHourlySlots(LocalDateTime entryDate, LocalDateTime exitDate) {
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

    public boolean isTimeSlotFree(TimeSlotDTO slot) {
        LocalDateTime startFreeTime = slot.start().withHour(freeTimeSlotConfig.getStartHour()).withMinute(freeTimeSlotConfig.getStartMinute());
        LocalDateTime endFreeTime = slot.end().withHour(freeTimeSlotConfig.getEndHour()).withMinute(freeTimeSlotConfig.getEndMinute());

        return (slot.start().isAfter(startFreeTime) || slot.start().isEqual(startFreeTime)) &&
                (slot.end().isBefore(endFreeTime) || slot.end().isEqual(endFreeTime));
    }
}
