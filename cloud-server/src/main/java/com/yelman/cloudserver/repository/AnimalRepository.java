package com.yelman.cloudserver.repository;

import com.yelman.cloudserver.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByCompany_Id(Long company_id);
}
