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

@Service
public class MyHrEmployeeServiceImpl implements MyHrEmployeeService {

    @Autowired
    private MyHrEmployeeRepository repository;

    @Override
    public Map<String, Object> addEmployee(Employee employee) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (repository.existsByNameAndAgeAndDepartment(employee.getName(), employee.getAge(),
                    employee.getDepartment())) {
                response.put("status", "error");
                response.put("errorCode", "DUPLICATE_EMPLOYEE");
                response.put("errorMessage", "Employee with same name, age, and department already exists.");
                return response;
            }
            repository.save(employee);
            response.put("status", "success");
        } catch (DataIntegrityViolationException e) {
            response.put("status", "error");
            response.put("errorCode", "INVALID_DATA");
            response.put("errorMessage", "Data integrity violation: " + e.getMessage());
        } catch (Exception e) {
            response.put("status", "error");
            response.put("errorCode", "DB_ERROR");
            response.put("errorMessage", "Database error: " + e.getMessage());
        }
        return response;
    }

    @Override
    public List<Object[]> listEmployees() {
        return repository.findAllIdAndName();
    }

    @Override
    public List<Employee> listEmployeesFiltered(String filter) {
        if (filter == null || filter.isEmpty()) {
            return repository.findAll();
        }
        return repository.findByNameContainingIgnoreCase(filter);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> emp = repository.findById(id);
        return emp.orElse(null);
    }

    @Override
    public Map<String, Object> updateEmployee(Employee employee) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Employee> existingEmp = repository.findById(employee.getId());
            if (existingEmp.isEmpty()) {
                response.put("status", "error");
                response.put("errorCode", "EMP_NOT_FOUND");
                response.put("errorMessage", "Employee not found for id: " + employee.getId());
                return response;
            }
            Employee empToUpdate = existingEmp.get();
            empToUpdate.setName(employee.getName());
            empToUpdate.setAge(employee.getAge());
            empToUpdate.setDepartment(employee.getDepartment());
            empToUpdate.setRank(employee.getRank());
            empToUpdate.setSupervisor(employee.getSupervisor());
            repository.save(empToUpdate);
            response.put("status", "success");
        } catch (DataIntegrityViolationException e) {
            response.put("status", "error");
            response.put("errorCode", "INVALID_DATA");
            response.put("errorMessage", "Data integrity violation: " + e.getMessage());
        } catch (Exception e) {
            response.put("status", "error");
            response.put("errorCode", "DB_ERROR");
            response.put("errorMessage", "Database error: " + e.getMessage());
        }
        return response;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteEmployeeById(Long employeeId) {

        Map<String, Object> response = new HashMap<>();

        if (!repository.existsById(employeeId)) {
            response.put("status", "error");
            response.put("errorCode", "EMP_NOT_FOUND");
            response.put("message", "Employee not found with id: " + employeeId);
            return response;
        }

        repository.deleteById(employeeId);

        response.put("status", "success");
        response.put("employeeId", employeeId);
        response.put("message", "Employee deleted successfully");

        return response;
    }

}
