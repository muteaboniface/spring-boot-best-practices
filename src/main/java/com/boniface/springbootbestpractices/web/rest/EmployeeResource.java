package com.boniface.springbootbestpractices.web.rest;

import com.boniface.springbootbestpractices.domain.Employee;
import com.boniface.springbootbestpractices.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping("/employees")
    public Employee create(@Validated @RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/employees")
    public Employee update(@RequestBody Employee employee) {
        return employeeService.update(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> read(@PathVariable Integer id) {

        Optional<Employee> optional = employeeService.read(id);

        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/employees")
    public List<Employee> readAll() {
        return employeeService.readAll();
    }

    @DeleteMapping("/employees/{id}")
    public void delete(@PathVariable Integer id) {
        employeeService.delete(id);
    }






}
