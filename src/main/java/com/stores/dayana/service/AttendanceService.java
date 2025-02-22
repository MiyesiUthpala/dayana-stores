package com.stores.dayana.service;

import com.stores.dayana.dto.EmployeeAttendanceProjection;
import com.stores.dayana.entity.Attendance;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AttendanceService {

    public Attendance markAttendance(Attendance attendance);

    public List<Attendance> getAttendance(LocalDate date);

    public List<EmployeeAttendanceProjection> getAttendanceSummary(LocalDate date);
}
