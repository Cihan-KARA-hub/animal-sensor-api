package com.yelman.cloudserver.repository;

import com.yelman.cloudserver.model.TemperatureHumidity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureHumidityRepository extends JpaRepository<TemperatureHumidity, Long> {
    Page<TemperatureHumidity> findByAnimal_Id(Long animalId,
                                              Pageable pageable);
}
