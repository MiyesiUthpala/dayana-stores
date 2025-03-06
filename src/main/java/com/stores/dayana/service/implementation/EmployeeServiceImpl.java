package com.stores.dayana.service.implementation;

import com.stores.dayana.entity.Employee;
import com.stores.dayana.repository.AttendanceRepository;
import com.stores.dayana.repository.EmployeeRepository;
import com.stores.dayana.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public List<Employee> getEmployeeList() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee addEmployeeList(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        attendanceRepository.deleteByEmployeeId(id);
        employeeRepository.deleteById(id);
    }
}
