package com.yelman.cloudserver.repository;

import com.yelman.cloudserver.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
