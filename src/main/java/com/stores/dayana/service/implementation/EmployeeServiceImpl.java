package com.stores.dayana.service.implementation;

import com.stores.dayana.entity.Employee;
import com.stores.dayana.repository.EmployeeRepository;
import com.stores.dayana.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployeeList() {
        return employeeRepository.findAll();
    }
}
