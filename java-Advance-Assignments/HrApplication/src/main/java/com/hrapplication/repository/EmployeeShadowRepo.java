package com.hrapplication.repository;

import com.hrapplication.entity.EmployeeShadow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeShadowRepo extends JpaRepository<EmployeeShadow, Long> {
}
