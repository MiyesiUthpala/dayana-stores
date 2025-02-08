package com.stores.dayana.controller;

import com.stores.dayana.entity.Attendance;
import com.stores.dayana.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    public Attendance markAttendance(@RequestBody Attendance attendance)
    {
        return attendanceService.markAttendance(attendance);
    }

}
