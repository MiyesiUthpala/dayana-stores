package com.stores.dayana.service.implementation;

import com.stores.dayana.entity.Attendance;
import com.stores.dayana.entity.Employee;
import com.stores.dayana.repository.AttendanceRepository;
import com.stores.dayana.repository.EmployeeRepository;
import com.stores.dayana.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public List<Employee> getEmployeeList() {
        List<Employee> employees = employeeRepository.findAll();

        // Map the employee list to include attendance and leave counts
        return employees.stream().map(employee -> {
            employee.setAttendanceCount(getAttendanceCount(employee.getId()));
            employee.setLeaveCount(getLeaveCount(employee.getId()));
            employee.setAttendanceStatus(getAttendanceStatus(employee.getId()));
            return employee;
        }).collect(Collectors.toList());
    }

    @Override
    public Employee addEmployeeList(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        // Set attendance and leave counts for the employee
        employee.setAttendanceCount(getAttendanceCount(id));
        employee.setLeaveCount(getLeaveCount(id));

        return employee;
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        attendanceRepository.deleteByEmployeeId(id);
        employeeRepository.deleteById(id);
    }

    // Helper method to get attendance count for the current month and year
    private int getAttendanceCount(Long employeeId) {
        LocalDate now = LocalDate.now();
        return attendanceRepository.countByEmployeeIdAndStatusAndMonthYear(employeeId, "Present", now.getMonthValue(), now.getYear());
    }

    // Helper method to get leave count for the current year
    private int getLeaveCount(Long employeeId) {
        LocalDate now = LocalDate.now();
        return attendanceRepository.countByEmployeeIdAndStatusAndYear(employeeId, "Absent", now.getYear());
    }

    private String getAttendanceStatus(Long employeeId) {
        LocalDate now = LocalDate.now();
        Optional<String> attendance = attendanceRepository.getAttendanceStatus(employeeId, now);
        String status = "";
        status = attendance.orElse("-");
//        attendance = attendanceRepository.getAttendanceStatus(employeeId, now);
        return status;
    }
}
