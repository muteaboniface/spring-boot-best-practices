package com.boniface.springbootbestpractices.repository;

import com.boniface.springbootbestpractices.domain.Employee;
import com.boniface.springbootbestpractices.domain.enums.EmployeeRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Query method
    List<Employee> findAllByRole(EmployeeRole role);
    Page<Employee> findAllByRole(EmployeeRole role, Pageable pageable);


    //search multiple
    List<Employee> findAllByNameContainingOrDepartmentNameContainingOrDepartmentCompanyCompanyNameContaining(String name, String departmentName, String companyName);
    Page<Employee> findAllByNameContainingOrDepartmentNameContainingOrDepartmentCompanyCompanyNameContaining(String name, String departmentName, String companyName, Pageable pageable);


    // JPQL
    @Query("SELECT e FROM Employee e WHERE e.role = 'SUPERVISOR'")
    List<Employee> findAllSupervisors(); // Here, the name doesn't matter

    @Query("SELECT e FROM Employee e WHERE e.role = 'SUPERVISOR'")
    Page<Employee> findAllSupervisors(Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name% OR e.department.name LIKE %:name% OR e.department.company.companyName LIKE %:name%")
    List<Employee> searchByNameMultiple(@Param("name") String name);
    // page
    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name% OR e.department.name LIKE %:name% OR e.department.company.companyName LIKE %:name%")
    Page<Employee> searchByNameMultiple(@Param("name") String name, Pageable pageable);

    // or using parameter position
    @Query("SELECT e FROM Employee e WHERE e.name LIKE %?1% OR e.department.name LIKE %?1% OR e.department.company.companyName LIKE %?1%")
    List<Employee> searchByName(String name);

    // page
    @Query("SELECT e FROM Employee e WHERE e.name LIKE %?1% OR e.department.name LIKE %?1% OR e.department.company.companyName LIKE %?1%")
    Page<Employee> searchByName(String name, Pageable pageable);



    // Native SQL
    @Query(value = "SELECT * FROM employees WHERE role = 'SUPERVISOR'", nativeQuery = true)
    List<Employee> findAllSupervisorsNative();

    // Page - We need to provide the count query
    @Query(value = "SELECT * FROM employees WHERE role = 'SUPERVISOR'", countQuery = "SELECT COUNT(*) FROM employees WHERE role = 'SUPERVISOR'", nativeQuery = true)
    Page<Employee> findAllSupervisorsNativePageable(Pageable pageable);

    // Search Multiple
    @Query(value = "SELECT * FROM employees WHERE name LIKE %:name% OR department_name LIKE %:name% OR company_name LIKE %:name%", nativeQuery = true)
    List<Employee> searchByNameNativeMultiple(@Param("name") String name);

    // page
    @Query(value = "SELECT * FROM employees WHERE name LIKE %:name% OR department_name LIKE %:name% OR company_name LIKE %:name%", countQuery = "SELECT COUNT(*) FROM employees WHERE name LIKE %:name% OR department_name LIKE %:name% OR company_name LIKE %:name%", nativeQuery = true)
    Page<Employee> searchByNameNativeMultiple(@Param("name") String name, Pageable pageable);

    Page<Employee> findAll(Specification<Employee> specification, Pageable pageable);

}