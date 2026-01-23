package com.hrapplication.service;

import com.hrapplication.entity.Employee;
import com.hrapplication.repository.MyHrEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.hrapplication.service.MyHrEmployeeService;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MyHrEmployeeServiceImpl implements MyHrEmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(MyHrEmployeeService.class);

    @Autowired
    private MyHrEmployeeRepository repository;

    @Autowired
    private EmployeeShadowService employeeShadowService;

    @Override
    public Map<String, Object> addEmployee(Employee employee) {

        logger.info("Add Employee service called");
        logger.debug("Employee payload: {}", employee);

        Map<String, Object> response = new HashMap<>();
        try {
            if (repository.existsByNameAndAgeAndDepartment(employee.getName(), employee.getAge(),
                    employee.getDepartment())) {
                logger.warn("Duplicate employee detected: {} {} {}", employee.getName(),
                        employee.getAge(), employee.getDepartment());
                response.put("status", "error");
                response.put("errorCode", "DUPLICATE_EMPLOYEE");
                response.put("errorMessage", "Employee with same name, age, and department already exists.");
                return response;
            }
            repository.save(employee);
            logger.info("Employee added successfully with id={}", employee.getId());
            response.put("status", "success");
        } catch (DataIntegrityViolationException e) {
            logger.error("Data integrity violation while adding employee: {}", employee, e);
            response.put("status", "error");
            response.put("errorCode", "INVALID_DATA");
            response.put("errorMessage", "Data integrity violation: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Database error while adding employee: {}", employee, e);
            response.put("status", "error");
            response.put("errorCode", "DB_ERROR");
            response.put("errorMessage", "Database error: " + e.getMessage());
        }
        return response;
    }

    @Override
    public List<Object[]> listEmployees() {
        logger.info("Listing all employees (id + name)");
        List<Object[]> employees = repository.findAllIdAndName();
        logger.debug("Number of employees fetched: {}", employees.size());
        return employees;
    }

    @Override
    public List<Employee> listEmployeesFiltered(String filter) {
        if (filter == null || filter.isEmpty()) {
            logger.info("Listing all employees without filter");
            return repository.findAll();
        }
        logger.info("Listing employees with filter: {}", filter);
        return repository.findByNameContainingIgnoreCase(filter);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        logger.info("Fetching employee by id={}", id);
        Optional<Employee> emp = repository.findById(id);
        if (emp.isEmpty()) {
            logger.warn("Employee not found with id={}", id);
        } else {
            logger.debug("Employee fetched: {}", emp.get());
        }
        return emp.orElse(null);
    }

    @Override
    public Map<String, Object> updateEmployee(Employee employee) {

        logger.info("Update Employee service called for id={}", employee.getId());
        logger.debug("Update payload: {}", employee);

        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Employee> existingEmp = repository.findById(employee.getId());
            if (existingEmp.isEmpty()) {

                logger.warn("Employee not found for update, id={}", employee.getId());
                response.put("status", "error");
                response.put("errorCode", "EMP_NOT_FOUND");
                response.put("errorMessage", "Employee not found for id: " + employee.getId());
                return response;

            }
            Employee empToUpdate = existingEmp.get();

            employeeShadowService.copyEmployeeToShadow(empToUpdate, "UPDATE");
            logger.debug("Copied employee id={} to shadow before update", empToUpdate.getId());

            empToUpdate.setName(employee.getName());
            empToUpdate.setAge(employee.getAge());
            empToUpdate.setDepartment(employee.getDepartment());
            empToUpdate.setRank(employee.getRank());
            empToUpdate.setSupervisor(employee.getSupervisor());
            repository.save(empToUpdate);
            logger.info("Employee updated successfully, id={}", empToUpdate.getId());

            response.put("status", "success");
        }

        catch (DataIntegrityViolationException e) {
            logger.error("Data integrity violation while updating employee: {}", employee, e);
            response.put("status", "error");
            response.put("errorCode", "INVALID_DATA");
            response.put("errorMessage", "Data integrity violation: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Database error while updating employee: {}", employee, e);
            response.put("status", "error");
            response.put("errorCode", "DB_ERROR");
            response.put("errorMessage", "Database error: " + e.getMessage());
        }
        return response;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteEmployeeById(Long employeeId) {

        logger.info("Delete Employee service called for id={}", employeeId);
        Map<String, Object> response = new HashMap<>();

        if (!repository.existsById(employeeId)) {

            logger.warn("Employee not found for delete, id={}", employeeId);
            response.put("status", "error");
            response.put("errorCode", "EMP_NOT_FOUND");
            response.put("message", "Employee not found with id: " + employeeId);
            return response;
        }

        try {
            // Copy to shadow before deletion
            Optional<Employee> emp = repository.findById(employeeId);
            emp.ifPresent(e -> employeeShadowService.copyEmployeeToShadow(e, "DELETE"));
            logger.debug("Copied employee id={} to shadow before delete", employeeId);

            // Delete employee
            repository.deleteById(employeeId);
            logger.info("Employee deleted successfully, id={}", employeeId);

            response.put("status", "success");
            response.put("employeeId", employeeId);
            response.put("message", "Employee deleted successfully");

        } catch (Exception e) {
            logger.error("Database error while deleting employee id={}", employeeId, e);
            response.put("status", "error");
            response.put("errorCode", "DB_ERROR");
            response.put("errorMessage", "Database error: " + e.getMessage());
        }

        return response;
    }

}
