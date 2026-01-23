package com.hrapplication.controller;

import com.hrapplication.entity.Employee;
import com.hrapplication.entity.EmployeeShadow;
import com.hrapplication.service.MyHrEmployeeService;
import com.hrapplication.service.EmployeeShadowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/myhr/employee")
public class MyHrEmployeeController {

    @Autowired
    private MyHrEmployeeService employeeService;

    @Autowired
    private EmployeeShadowService employeeShadowService;

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

    // 6. Add employee to shadow table
    @PostMapping("/shadow/add")
    public EmployeeShadow addEmployeeShadow(@RequestBody EmployeeShadow employeeShadow) {
        return employeeShadowService.addEmployeeShadow(employeeShadow);
    }

    // 7. List all shadow employees
    @GetMapping("/shadow/list")
    public List<EmployeeShadow> listEmployeeShadows() {
        return employeeShadowService.getAllEmployeeShadows();
    }

    // DELETE employee (REST-compliant)
    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployeeById(id);
    }

}
