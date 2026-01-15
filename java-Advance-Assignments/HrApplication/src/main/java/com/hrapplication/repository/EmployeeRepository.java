package com.hrapplication.repository;

import com.hrapplication.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Check for duplicate employee by name + department + age
    Optional<Employee> findByNameAndDepartmentAndAge(String name, String department, int age);
}
