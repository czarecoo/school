package com.czareg.school.feature.billing.common.component;

import com.czareg.school.config.FreeTimeSlotConfig;
import com.czareg.school.feature.billing.common.dto.TimeSlotDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeSlotManagerTest {

    public static final FreeTimeSlotConfig FREE_TIME_SLOT_CONFIG = new FreeTimeSlotConfig(6, 30, 10, 45);
    private final LocalDateTime ORIGIN = LocalDateTime.of(2000, 1, 1, 7, 0);
    private TimeSlotManager timeSlotManager;

    @BeforeEach
    void setUp() {
        timeSlotManager = new TimeSlotManager(FREE_TIME_SLOT_CONFIG);
    }

    @Test
    void shouldReturnCorrectNumberOfSlots() {
        LocalDateTime entryDate = ORIGIN.withHour(8).withMinute(0);
        LocalDateTime exitDate = ORIGIN.withHour(12).withMinute(30);

        List<TimeSlotDTO> slots = timeSlotManager.splitTimeIntoHourlySlots(entryDate, exitDate);

        assertEquals(5, slots.size());
    }

    @Test
    void shouldReturnSlotsWithCorrectTimes() {
        LocalDateTime entryDate = ORIGIN.withHour(8).withMinute(15);
        LocalDateTime exitDate = ORIGIN.withHour(11).withMinute(45);

        List<TimeSlotDTO> slots = timeSlotManager.splitTimeIntoHourlySlots(entryDate, exitDate);

        assertEquals(ORIGIN.withHour(8).withMinute(15), slots.get(0).start());
        assertEquals(ORIGIN.withHour(9).withMinute(0), slots.get(0).end());
        assertEquals(ORIGIN.withHour(9).withMinute(0), slots.get(1).start());
        assertEquals(ORIGIN.withHour(10).withMinute(0), slots.get(1).end());
        assertEquals(ORIGIN.withHour(10).withMinute(0), slots.get(2).start());
        assertEquals(ORIGIN.withHour(11).withMinute(0), slots.get(2).end());
        assertEquals(ORIGIN.withHour(11).withMinute(0), slots.get(3).start());
        assertEquals(ORIGIN.withHour(11).withMinute(45), slots.get(3).end());

    }

    @ParameterizedTest(name = "Should return {0} for {1}")
    @MethodSource("generateTestCases")
    void shouldReturnExpectedValueForTimeslot(boolean expected, TimeSlotDTO timeSlotDTO) {
        assertEquals(expected, timeSlotManager.isTimeSlotFree(timeSlotDTO));
    }

    private static Stream<Arguments> generateTestCases() {
        LocalDateTime ORIGIN = LocalDateTime.of(2000, 1, 1, 7, 0);
        return Stream.of(
                Arguments.of(false, new TimeSlotDTO(ORIGIN.withHour(1).withMinute(0), ORIGIN.withHour(2).withMinute(0))),
                Arguments.of(false, new TimeSlotDTO(ORIGIN.withHour(5).withMinute(0), ORIGIN.withHour(6).withMinute(0))),
                Arguments.of(true, new TimeSlotDTO(ORIGIN.withHour(7).withMinute(0), ORIGIN.withHour(8).withMinute(0))),
                Arguments.of(true, new TimeSlotDTO(ORIGIN.withHour(6).withMinute(30), ORIGIN.withHour(6).withMinute(45))),
                Arguments.of(true, new TimeSlotDTO(ORIGIN.withHour(7).withMinute(30), ORIGIN.withHour(8).withMinute(30))),
                Arguments.of(true, new TimeSlotDTO(ORIGIN.withHour(6).withMinute(45), ORIGIN.withHour(7).withMinute(30))),
                Arguments.of(false, new TimeSlotDTO(ORIGIN.withHour(6).withMinute(29), ORIGIN.withHour(6).withMinute(30))),
                Arguments.of(false, new TimeSlotDTO(ORIGIN.withHour(6).withMinute(15), ORIGIN.withHour(7).withMinute(45))),
                Arguments.of(false, new TimeSlotDTO(ORIGIN.withHour(10).withMinute(15), ORIGIN.withHour(11).withMinute(0))),
                Arguments.of(false, new TimeSlotDTO(ORIGIN.withHour(5).withMinute(0), ORIGIN.withHour(6).withMinute(0))),
                Arguments.of(false, new TimeSlotDTO(ORIGIN.withHour(11).withMinute(0), ORIGIN.withHour(12).withMinute(0)))
        );
    }
}