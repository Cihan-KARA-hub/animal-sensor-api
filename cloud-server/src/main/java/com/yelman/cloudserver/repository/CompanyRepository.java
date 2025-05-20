package com.yelman.cloudserver.repository;

import com.yelman.cloudserver.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT a.id FROM Company a ")
    List<Long> findAllCompanyId();
    Company findByName(String username);
    Company findByUser_Id(Long id);
}
