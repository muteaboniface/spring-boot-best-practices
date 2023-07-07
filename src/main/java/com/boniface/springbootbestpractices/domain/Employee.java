package com.boniface.springbootbestpractices.domain;

import com.boniface.springbootbestpractices.domain.enums.EmployeeRole;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String company;

    // many employees can belong to one department
    @ManyToOne
    private Department department;

    // many employees can have many supervisors
    //    @ManyToMany
    //    private List<Employee> supervisors;

    // one employee can have many employee supervision reports
    @OneToMany
    private List<EmployeeSupervision> employeeSupervisions;

    private LocalDate dateOfBirth;

    private LocalDate dateOfEmployment;

    private Integer salary;

    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<EmployeeSupervision> getEmployeeSupervisions() {
        return employeeSupervisions;
    }

    public void setEmployeeSupervisions(List<EmployeeSupervision> employeeSupervisions) {
        this.employeeSupervisions = employeeSupervisions;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }
}