package com.yelman.cloudserver.repository;

import com.yelman.cloudserver.api.dto.VetDto;
import com.yelman.cloudserver.model.Animal;
import com.yelman.cloudserver.model.Company;
import com.yelman.cloudserver.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByCompany_Id(Long company_id);
}
