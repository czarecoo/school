package com.czareg.school.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FreeTimeSlotConfigTest {

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