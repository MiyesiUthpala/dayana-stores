package com.stores.dayana.service.implementation;

import com.stores.dayana.dto.EmployeeAttendanceProjection;
import com.stores.dayana.entity.Attendance;
import com.stores.dayana.repository.AttendanceRepository;
import com.stores.dayana.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Override
    public Attendance markAttendance(Attendance attendance) {
//        return attendanceRepository.save(attendance);
        Optional<Attendance> existingAttendance = attendanceRepository
                .findByEmployeeIdAndDate(attendance.getEmployeeId().getId(), attendance.getDate());

        if (existingAttendance.isPresent()) {
            // If attendance exists, update the status
            Attendance existing = existingAttendance.get();
            existing.setStatus(attendance.getStatus()); // Assuming status is the field to be updated
            return attendanceRepository.save(existing); // Save updated attendance
        } else {
            // If no attendance exists, save the new attendance
            return attendanceRepository.save(attendance);
        }
    }

    @Override
    public List<Attendance> getAttendance(LocalDate date) {
        return attendanceRepository.findByAttendanceDate(date);
    }

    @Override
    public List<EmployeeAttendanceProjection> getAttendanceSummary(LocalDate date) {
        return attendanceRepository.getEmployeeAttendance(date);
    }

}
