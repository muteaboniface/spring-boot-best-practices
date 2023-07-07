package com.boniface.springbootbestpractices.repository;

import com.boniface.springbootbestpractices.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
