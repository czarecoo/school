package com.czareg.school.feature.billing.common.component;

import com.czareg.school.config.FreeTimeSlotConfig;
import com.czareg.school.feature.attendence.Attendance;
import com.czareg.school.feature.billing.common.dto.ChildBillingDTO;
import com.czareg.school.feature.billing.common.dto.TimeSlotDTO;
import com.czareg.school.feature.child.Child;
import com.czareg.school.feature.school.School;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChildBillingPreparerTest {

    private static final FreeTimeSlotConfig FREE_TIME_SLOT_CONFIG = new FreeTimeSlotConfig(7, 0, 12, 0);
    private final LocalDateTime ORIGIN = LocalDateTime.of(2000, 1, 1, 7, 0);
    private ChildBillingPreparer childBillingPreparer;

    @BeforeEach
    void setUp() {
        TimeSlotManager timeSlotManager = new TimeSlotManager(FREE_TIME_SLOT_CONFIG);
        childBillingPreparer = new ChildBillingPreparer(timeSlotManager);
    }

    @Test
    void shouldUseCorrectSchoolHourPrice() {
        List<Attendance> attendanceList = createAttendence();
        BigDecimal expectedHourPrice = new BigDecimal("11.99");
        School school = createSchool(expectedHourPrice);

        ChildBillingDTO result = childBillingPreparer.prepare(attendanceList, school);

        assertEquals(expectedHourPrice, result.hourPrice());
    }

    @Test
    void shouldReturnEmptyChildBillingWhenAttendanceListIsEmpty() {
        List<Attendance> attendanceList = createAttendence();
        School school = createSchool();

        ChildBillingDTO result = childBillingPreparer.prepare(attendanceList, school);

        assertEquals(BigDecimal.ZERO, result.totalBillingAmount());
        assertEquals(0, result.billableHours());
        assertEquals(0, result.freeHours());
        assertEquals(0, result.totalHours());
        assertEquals(List.of(), result.billableTimeslots());
    }

    @Test
    void shouldReturnEmptyChildBillingWhenThereAreNoBillableHours() {
        List<Attendance> attendanceList = createAttendence(
                Pair.of(
                        ORIGIN.withHour(7).withMinute(0),
                        ORIGIN.withHour(8).withMinute(0)),
                Pair.of(
                        ORIGIN.withHour(9).withMinute(0),
                        ORIGIN.withHour(10).withMinute(0)
                ));
        School school = createSchool();

        ChildBillingDTO result = childBillingPreparer.prepare(attendanceList, school);

        assertEquals(BigDecimal.ZERO, result.totalBillingAmount());
        assertEquals(0, result.billableHours());
        assertEquals(2, result.freeHours());
        assertEquals(2, result.totalHours());
        assertEquals(List.of(), result.billableTimeslots());
    }

    @Test
    void shouldCorrectlyCalculateHoursAndTotalBillingAmount() {
        List<Attendance> attendanceList = createAttendence(
                Pair.of(
                        ORIGIN.withHour(6).withMinute(0),
                        ORIGIN.withHour(13).withMinute(0)
                ));
        School school = createSchool("6.69");

        ChildBillingDTO result = childBillingPreparer.prepare(attendanceList, school);

        assertEquals(new BigDecimal("13.38"), result.totalBillingAmount());
        assertEquals(2, result.billableHours());
        assertEquals(5, result.freeHours());
        assertEquals(7, result.totalHours());
        assertEquals(List.of(
                        new TimeSlotDTO(ORIGIN.withHour(6).withMinute(0), ORIGIN.withHour(7).withMinute(0)),
                        new TimeSlotDTO(ORIGIN.withHour(12).withMinute(0), ORIGIN.withHour(13).withMinute(0))),
                result.billableTimeslots());
    }

    @SafeVarargs
    private List<Attendance> createAttendence(Pair<LocalDateTime, LocalDateTime>... dates) {
        List<Attendance> attendanceList = new ArrayList<>();
        Child child = new Child(0L, "Bob", "Newman", null, null);
        long index = 0;
        for (Pair<LocalDateTime, LocalDateTime> pair : dates) {
            attendanceList.add(new Attendance(index, child, pair.getFirst(), pair.getSecond()));
            index++;
        }
        return attendanceList;
    }

    private School createSchool() {
        return createSchool("1");
    }

    private School createSchool(String hourPrice) {
        return createSchool(new BigDecimal(hourPrice));
    }

    private School createSchool(BigDecimal hourPrice) {
        return new School(0L, "Test School", hourPrice);
    }
}