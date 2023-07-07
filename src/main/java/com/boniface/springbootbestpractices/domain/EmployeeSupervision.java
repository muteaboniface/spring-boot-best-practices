package com.boniface.springbootbestpractices.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_supervision")
public class EmployeeSupervision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Employee supervisor;
    private Integer hoursSupervised;
    private String natureOfSupervision;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }

    public Integer getHoursSupervised() {
        return hoursSupervised;
    }

    public void setHoursSupervised(Integer hoursSupervised) {
        this.hoursSupervised = hoursSupervised;
    }

    public String getNatureOfSupervision() {
        return natureOfSupervision;
    }

    public void setNatureOfSupervision(String natureOfSupervision) {
        this.natureOfSupervision = natureOfSupervision;
    }
}
