package com.yelman.cloudserver.repository;

import com.yelman.cloudserver.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByCompany_Id(Long company_id);

    @Query("SELECT a.id FROM Animal a")
    List<Long> findAllAnimalId();

    @Query("SELECT a.id FROM Animal a WHERE a.company.id = :companyId")
    List<Long> findAllAnimalIdByCompany_Id(@Param("companyId") Long companyId);
}
