package com.yelman.cloudserver.repository;

import com.yelman.cloudserver.model.ChewingActivity;
import com.yelman.cloudserver.model.HeartBeat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HeartBeatRepository extends JpaRepository<HeartBeat, Long> {
    Page<HeartBeat> findByAnimal_Id(Long animalId,
                                          Pageable pageable);
}
