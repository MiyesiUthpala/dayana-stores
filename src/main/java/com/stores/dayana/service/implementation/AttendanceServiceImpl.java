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

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Override
    public Attendance markAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
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
