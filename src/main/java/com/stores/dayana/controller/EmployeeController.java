package com.stores.dayana.controller;

import com.stores.dayana.entity.Employee;
import com.stores.dayana.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public List<Employee> getEmployees()
    {
        return employeeService.getEmployeeList();
    }

    @PostMapping(path = "/add")
    public Employee addEmployee(@RequestBody Employee employee)
    {
        return  employeeService.addEmployeeList(employee);
    }

    @GetMapping
    public Employee getEmployeeById(@RequestParam Long id)
    {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping
    public void deleteEmployee(@RequestParam Long id)
    {
        employeeService.deleteEmployee(id);
    }
}
