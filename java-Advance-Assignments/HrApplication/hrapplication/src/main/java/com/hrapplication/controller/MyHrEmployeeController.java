package com.hrapplication.controller;

import com.hrapplication.entity.Employee;
import com.hrapplication.entity.EmployeeShadow;
import com.hrapplication.service.MyHrEmployeeService;
import com.hrapplication.service.EmployeeShadowService;
import com.hrapplication.entity.EmployeeListResponse; //added for Q17
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus; //Added for Q19

import com.hrapplication.service.MyHrEmployeeService;

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
    /*
     * @GetMapping("/list")
     * public List<Object[]> listEmployees() {
     * logger.info("LIST Employees API called");
     * return employeeService.listEmployees();
     * }
     */

    // 3 .list for Q19 , Q17
    @GetMapping("/list")
    public ResponseEntity<?> listEmployees(
            @RequestParam(required = false) String type) {

        logger.info("LIST Employees API called with type={}", type);

        try {

            if ("xml".equalsIgnoreCase(type)) {
                List<Object[]> employees = employeeService.listEmployees();
                return ResponseEntity
                        .ok()
                        .header("Content-Type", "application/xml")
                        .body(new EmployeeListResponse(employees));
            }

            else if ("xlsx".equalsIgnoreCase(type)) {
                return employeeService.downloadEmployeeListAsExcel();
            }

            else if ("pdf".equalsIgnoreCase(type)) {
                return employeeService.generateEmployeePdf();
            }

            else {
                return ResponseEntity.ok(employeeService.getAllEmployees());
            }

        } catch (Exception e) {

            logger.error("Error while listing employees", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
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
