package com.czareg.school.feature.attendence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendenceService {

    private final AttendanceRepository attendanceRepository;

    public List<Attendance> findByChildIdAndMonth(long childId, int month) {
        return attendanceRepository.findByChildIdAndMonth(childId, month);
    }
}
