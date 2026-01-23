package com.hrapplication.service;

import com.hrapplication.entity.Employee;
import com.hrapplication.entity.EmployeeShadow;
import com.hrapplication.repository.EmployeeShadowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeShadowServiceImpl implements EmployeeShadowService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeShadowServiceImpl.class);

    @Autowired
    private EmployeeShadowRepo repository;

    @Override
    public EmployeeShadow addEmployeeShadow(EmployeeShadow employeeShadow) {
        logger.info("Adding new employee shadow record");
        logger.debug("EmployeeShadow payload: {}", employeeShadow);
        EmployeeShadow savedShadow;
        try {
            savedShadow = repository.save(employeeShadow);
            logger.info("EmployeeShadow saved successfully with id={}", savedShadow.getId());
        } catch (Exception e) {
            logger.error("Failed to save EmployeeShadow record", e);
            throw e;
        }
        return savedShadow;
    }

    @Override
    public List<EmployeeShadow> getAllEmployeeShadows() {
        logger.info("Fetching all employee shadow records");
        List<EmployeeShadow> shadows = repository.findAll();
        logger.debug("Number of shadow records fetched: {}", shadows.size());
        return shadows;
    }

    @Override
    public void copyEmployeeToShadow(Employee employee, String operationType) {

        logger.info("Copying employee '{}' to shadow table with operation '{}'", employee.getName(),
                operationType);

        EmployeeShadow shadow = new EmployeeShadow();
        shadow.setName(employee.getName());
        shadow.setAge(employee.getAge());
        shadow.setDepartment(employee.getDepartment());
        shadow.setRank(employee.getRank());
        shadow.setSupervisor(employee.getSupervisor());

        logger.debug("Prepared shadow object: {}", shadow);

        try {
            repository.save(shadow);
            logger.info("Shadow record saved successfully for employee '{}'", employee.getName());
        } catch (Exception e) {
            logger.error("Failed to save shadow record for employee '{}'", employee.getName(), e);
        }
    }
}
