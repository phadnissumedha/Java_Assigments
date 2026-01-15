package com.hrapplication.service;

import com.hrapplication.entity.Employee;
import com.hrapplication.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Save employee only if not duplicate
    public Employee saveEmployee(Employee employee) {
        boolean exists = employeeRepository
                .findByNameAndDepartmentAndAge(employee.getName(), employee.getDepartment(), employee.getAge())
                .isPresent();

        if (exists) {
            throw new IllegalArgumentException(
                    "Employee with same name, department, and age already exists!");
        }

        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
