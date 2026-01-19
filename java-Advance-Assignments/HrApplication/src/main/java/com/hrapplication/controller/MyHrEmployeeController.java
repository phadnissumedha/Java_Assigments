package com.hrapplication.controller;

import com.hrapplication.entity.Employee;
import com.hrapplication.service.MyHrEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/myhr/employee")
public class MyHrEmployeeController {

    @Autowired
    private MyHrEmployeeService employeeService;

    // 1. Add employee
    @PostMapping("/add")
    public Map<String, Object> addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    // 2. List all employees (id + name)
    @GetMapping("/list")
    public List<Object[]> listEmployees() {
        return employeeService.listEmployees();
    }

    // 3. List employees with optional filter (name)
    @PostMapping("/list")
    public List<Employee> listEmployeesFiltered(@RequestParam(required = false) String filter) {
        return employeeService.listEmployeesFiltered(filter);
    }

    // 4. Get employee by id
    @GetMapping("/get")
    public Employee getEmployee(@RequestParam Long id) {
        return employeeService.getEmployeeById(id);
    }

    // 5. Update employee
    @PostMapping("/update")
    public Map<String, Object> updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }
}
