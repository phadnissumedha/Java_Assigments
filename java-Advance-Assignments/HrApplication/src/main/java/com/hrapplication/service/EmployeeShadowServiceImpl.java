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
