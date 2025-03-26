package com.yelman.cloudserver.repository;

import com.yelman.cloudserver.model.Company;
import com.yelman.cloudserver.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VetRepository extends JpaRepository<Vet, Long> {
    Optional<Vet> findByResponsibleCompany(Company responsibleCompany);
}
