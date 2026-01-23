/*
package com.hrapplication.service;

import com.hrapplication.entity.EmployeeShadow;
import com.hrapplication.repository.EmployeeShadowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeShadowServiceImpl implements EmployeeShadowService {

    @Autowired
    private EmployeeShadowRepo repository;

    @Override
    public EmployeeShadow addEmployeeShadow(EmployeeShadow employeeShadow) {
        return repository.save(employeeShadow);
    }

    @Override
    public List<EmployeeShadow> getAllEmployeeShadows() {
        return repository.findAll();
    }
}
*/

package com.hrapplication.service;

import com.hrapplication.entity.Employee;
import com.hrapplication.entity.EmployeeShadow;
import com.hrapplication.repository.EmployeeShadowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeShadowServiceImpl implements EmployeeShadowService {

    @Autowired
    private EmployeeShadowRepo repository;

    @Override
    public EmployeeShadow addEmployeeShadow(EmployeeShadow employeeShadow) {
        return repository.save(employeeShadow);
    }

    @Override
    public List<EmployeeShadow> getAllEmployeeShadows() {
        return repository.findAll();
    }

    @Override
    public void copyEmployeeToShadow(Employee employee, String operationType) {

        EmployeeShadow shadow = new EmployeeShadow();
        shadow.setName(employee.getName());
        shadow.setAge(employee.getAge());
        shadow.setDepartment(employee.getDepartment());
        shadow.setRank(employee.getRank());
        shadow.setSupervisor(employee.getSupervisor());

        repository.save(shadow);
    }
}
