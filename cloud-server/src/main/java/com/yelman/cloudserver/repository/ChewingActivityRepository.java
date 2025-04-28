package com.yelman.cloudserver.repository;

import com.yelman.cloudserver.model.ChewingActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ChewingActivityRepository extends JpaRepository<ChewingActivity, Long> {
    Page<ChewingActivity> findByAnimal_Id(Long animalId,
                                          Pageable pageable);
}
