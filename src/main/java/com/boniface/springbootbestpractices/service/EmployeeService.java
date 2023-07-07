package com.boniface.springbootbestpractices.service;

import com.boniface.springbootbestpractices.domain.Company;
import com.boniface.springbootbestpractices.domain.Department;
import com.boniface.springbootbestpractices.domain.Employee;
import com.boniface.springbootbestpractices.repository.EmployeeRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> read(Integer id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> readAll() {
        return employeeRepository.findAll();
    }

    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }


    Page<Employee> searchByName(String name, Pageable pageable) {
        return employeeRepository.
                findAllByNameContainingOrDepartmentNameContainingOrDepartmentCompanyCompanyNameContaining(
                        name, name, name, pageable);
    }


    // Query by Example
    public Page<Employee> searchByExample(String name, Pageable pageable) {
        // first, create an example object
        Employee employee = new Employee();
        employee.setName(name);
        Department department = new Department();
        department.setName(name);
        Company company = new Company();
        company.setCompanyName(name);
        department.setCompany(company);
        employee.setDepartment(department);
        // then, create an example matcher
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("department.name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("department.company.companyName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        // then, create an example
        Example<Employee> example = Example.of(employee, matcher);
        // then, use it
        return employeeRepository.findAll(example, pageable);
    }


    // Search by specification
    public Page<Employee> search(String name, Pageable pageable) {
        Specification<Employee> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("department").get("name")), "%" + name.toLowerCase() + "%"));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("department").get("company").get("companyName")), "%" + name.toLowerCase() + "%"));
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };

        return employeeRepository.findAll(specification, pageable);
    }

}