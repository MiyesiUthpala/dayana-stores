package com.stores.dayana.repository;

import com.stores.dayana.dto.EmployeeAttendanceProjection;
import com.stores.dayana.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {

    @Query("SELECT a FROM Attendance a WHERE a.date = :date")
    List<Attendance> findByAttendanceDate(@Param("date") LocalDate date);

    @Query(value = """
    SELECT 
        e.id AS employeeId,
        e.first_name AS firstName,
        e.last_name AS lastName,
        COUNT(a.attendance_id) AS attendanceCount,
        (DAY(LAST_DAY(STR_TO_DATE(:month, '%Y-%m-%d'))) - COUNT(a.attendance_id)) AS leaveCount
    FROM employee e
    LEFT JOIN attendance a 
        ON e.id = a.employee_id 
        AND DATE_FORMAT(a.date, '%Y-%m') = DATE_FORMAT(:month, '%Y-%m')
    GROUP BY e.id, e.first_name, e.last_name
    """, nativeQuery = true)
    List<EmployeeAttendanceProjection> getEmployeeAttendance(@Param("month") LocalDate date);

    @Modifying
    @Transactional
    @Query("DELETE FROM Attendance a WHERE a.employee.id = :employeeId")
    void deleteByEmployeeId(@Param("employeeId") Long employeeId);
}
