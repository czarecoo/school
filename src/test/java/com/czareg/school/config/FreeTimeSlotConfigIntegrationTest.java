package com.czareg.school.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = {
        "free.time.slot.startHour=3",
        "free.time.slot.startMinute=39",
        "free.time.slot.endHour=18",
        "free.time.slot.endMinute=3"
})
class FreeTimeSlotConfigIntegrationTest {

    @Autowired
    private FreeTimeSlotConfig freeTimeSlotConfig;

    @Test
    void shouldAutowireBeanAndLoadCorrectTestProperties() {
        assertNotNull(freeTimeSlotConfig);
        assertEquals(3, freeTimeSlotConfig.getStartHour());
        assertEquals(39, freeTimeSlotConfig.getStartMinute());
        assertEquals(18, freeTimeSlotConfig.getEndHour());
        assertEquals(3, freeTimeSlotConfig.getEndMinute());
    }
}