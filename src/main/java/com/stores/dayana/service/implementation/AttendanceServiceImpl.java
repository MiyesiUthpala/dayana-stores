package com.stores.dayana.service.implementation;

import com.stores.dayana.entity.Attendance;
import com.stores.dayana.repository.AttendanceRepository;
import com.stores.dayana.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Override
    public Attendance markAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

}
