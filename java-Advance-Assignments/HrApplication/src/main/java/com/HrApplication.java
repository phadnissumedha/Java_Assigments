package com.hrapplication;

import com.hrapplication.entity.Employee;
import com.hrapplication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(HrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Save demo employee at startup
        Employee employee = new Employee();
        employee.setName("Sumedha");
        employee.setDepartment("IT");
        employee.setAge(28);
        employee.setDateOfJoining("2024-06-25");

        employeeService.saveEmployee(employee);

        System.out.println("Demo employee saved successfully!");
    }
}
