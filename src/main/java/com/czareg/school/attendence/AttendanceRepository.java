package com.czareg.school.attendence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT a FROM Attendance a WHERE a.child.id = :childId AND FUNCTION('MONTH', a.entryDate) = :month")
    List<Attendance> findByChildIdAndMonth(Long childId, int month);
}
