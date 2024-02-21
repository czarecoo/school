package com.czareg.school.feature.attendence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class AttendanceRepositoryTest {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Test
    void shouldReturnEmptyListOfAttendanceForJanuary() {
        List<Attendance> attendanceList = attendanceRepository.findByChildIdAndMonth(1L, 1);

        assertTrue(attendanceList.isEmpty());
    }

    @Test
    void shouldReturnEmptyListOfAttendanceForNotExistingChild() {
        List<Attendance> attendanceList = attendanceRepository.findByChildIdAndMonth(999L, 2);

        assertTrue(attendanceList.isEmpty());
    }

    @Test
    void shouldReturnListOfFiveAttendanceForFebruary() {
        List<Attendance> attendanceList = attendanceRepository.findByChildIdAndMonth(1L, 2);

        assertEquals(5, attendanceList.size());
    }
}