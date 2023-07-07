package com.boniface.springbootbestpractices.repository;

import com.boniface.springbootbestpractices.domain.Company;
import com.boniface.springbootbestpractices.service.EmployeeService;
import com.boniface.springbootbestpractices.web.rest.HelloController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test; // v5
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest // starts the entire application context
//@WebMvcTest(CompanyRepository.class)
public class CompaniesRepositoryTest {
    @Autowired
    CompanyRepository companyRepository;

    @Test
    void testCompanyCreation() {
        long before = companyRepository.count();
        companyRepository.save(new Company(6,"TEST1","Nairobi"));
        companyRepository.findAll().forEach(System.out::println);
        Assertions.assertEquals(before + 1, companyRepository.count());
    }
}