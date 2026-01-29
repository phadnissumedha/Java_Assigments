//added for Q17
package com.hrapplication.entity;

public class EmployeeSummary {

    private Long id;
    private String name;

    public EmployeeSummary() {
    }

    public EmployeeSummary(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
