package com.hrapplication.service;

import com.hrapplication.entity.Employee;

import java.util.List;
import java.util.Map;

public interface MyHrEmployeeService {

    Map<String, Object> addEmployee(Employee employee);

    List<Object[]> listEmployees();

    List<Employee> listEmployeesFiltered(String filter);

    Employee getEmployeeById(Long id);

    Map<String, Object> updateEmployee(Employee employee);

    Map<String, Object> deleteEmployeeById(Long employeeId);

}
