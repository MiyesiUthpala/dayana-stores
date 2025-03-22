package com.stores.dayana.service;

import com.stores.dayana.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getEmployeeList();
    public Employee addEmployeeList(Employee employee);
    public Employee getEmployeeById(Long id);
    public void deleteEmployee(Long id);
}
