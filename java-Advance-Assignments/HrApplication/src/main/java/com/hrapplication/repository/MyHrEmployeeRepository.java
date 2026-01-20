package com.hrapplication.repository;

import com.hrapplication.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyHrEmployeeRepository extends JpaRepository<Employee, Long> {

    // Check for duplicates (name + age + department)
    boolean existsByNameAndAgeAndDepartment(String name, Integer age, String department);

    // Filtered list (case insensitive)
    @Query("SELECT e FROM Employee e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :filter, '%'))")
    List<Employee> findByNameContainingIgnoreCase(@Param("filter") String filter);

    // Custom select id + name
    @Query("SELECT e.id, e.name FROM Employee e")
    List<Object[]> findAllIdAndName();

    @Query("""
                SELECT
                    e.id,
                    e.name,
                    e.rank,
                    e.department,
                    e.supervisor
                FROM Employee e
                WHERE e.id = :employeeId
            """)
    Object[] getEmployeeDeleteDetails(@Param("employeeId") Long id);
}
