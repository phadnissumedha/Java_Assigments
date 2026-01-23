package com.hrapplication.controller;

import com.hrapplication.entity.Employee;
import com.hrapplication.entity.EmployeeShadow;
import com.hrapplication.service.MyHrEmployeeService;
import com.hrapplication.service.EmployeeShadowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/myhr/employee")

public class MyHrEmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(MyHrEmployeeController.class);
    @Autowired
    private MyHrEmployeeService employeeService;

    @Autowired
    private EmployeeShadowService employeeShadowService;

    // 1. Add employee
    @PostMapping("/add")
    public Map<String, Object> addEmployee(@RequestBody Employee employee) {
        logger.info("ADD Employee API called");
        logger.debug("Request payload: {}", employee);
        return employeeService.addEmployee(employee);
    }

    // 2. List all employees (id + name)
    @GetMapping("/list")
    public List<Object[]> listEmployees() {
        logger.info("LIST Employees API called");
        return employeeService.listEmployees();
    }

    // 3. List employees with optional filter (name)
    @PostMapping("/list")
    public List<Employee> listEmployeesFiltered(@RequestParam(required = false) String filter) {
        logger.info("FILTER Employees API called");
        logger.debug("Filter value: {}", filter);
        return employeeService.listEmployeesFiltered(filter);
    }

    // 4. Get employee by id
    @GetMapping("/get")
    public Employee getEmployee(@RequestParam Long id) {
        logger.info("GET Employee API called for id={}", id);
        return employeeService.getEmployeeById(id);
    }

    // 5. Update employee
    @PostMapping("/update")
    public Map<String, Object> updateEmployee(@RequestBody Employee employee) {
        logger.info("UPDATE Employee API called");
        logger.debug("Update payload: {}", employee);
        return employeeService.updateEmployee(employee);
    }

    // 6. Add employee to shadow table
    @PostMapping("/shadow/add")
    public EmployeeShadow addEmployeeShadow(@RequestBody EmployeeShadow employeeShadow) {
        return employeeShadowService.addEmployeeShadow(employeeShadow);
    }

    // 7. List all shadow employees
    @GetMapping("/shadow/list")
    public List<EmployeeShadow> listEmployeeShadows() {
        logger.info("LIST Shadow Employees API called");
        return employeeShadowService.getAllEmployeeShadows();
    }

    // DELETE employee (REST-compliant)
    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteEmployee(@PathVariable Long id) {
        logger.warn("DELETE Employee API called for id={}", id);
        return employeeService.deleteEmployeeById(id);
    }

}
