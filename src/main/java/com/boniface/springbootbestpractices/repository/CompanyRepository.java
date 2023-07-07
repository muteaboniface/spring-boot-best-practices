package com.boniface.springbootbestpractices.repository;

import com.boniface.springbootbestpractices.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
}
