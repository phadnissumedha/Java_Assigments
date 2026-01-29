//added for Q17
package com.hrapplication.entity;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListResponse {

    private List<EmployeeSummary> employees = new ArrayList<>();

    public EmployeeListResponse() {
    }

    public EmployeeListResponse(List<Object[]> data) {
        for (Object[] row : data) {
            employees.add(
                    new EmployeeSummary(
                            (Long) row[0],
                            (String) row[1]));
        }
    }

    public List<EmployeeSummary> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeSummary> employees) {
        this.employees = employees;
    }
}
