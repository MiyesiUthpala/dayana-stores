package com.stores.dayana.controller;

import com.stores.dayana.dto.EmployeeAttendanceProjection;
import com.stores.dayana.entity.Attendance;
import com.stores.dayana.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @PostMapping("/mark")
    public Attendance markAttendance(@RequestBody Attendance attendance)
    {
        return attendanceService.markAttendance(attendance);
    }

    @GetMapping("/summary")
    public List<EmployeeAttendanceProjection> getAttendanceSummary(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return attendanceService.getAttendanceSummary(date);
    }

    @GetMapping
    public List<Attendance> getAttendance(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return attendanceService.getAttendance(date);
    }

}
