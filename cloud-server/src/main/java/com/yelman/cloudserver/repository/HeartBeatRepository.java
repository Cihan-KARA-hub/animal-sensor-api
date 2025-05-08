package com.yelman.cloudserver.repository;

import com.yelman.cloudserver.model.HeartBeat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface HeartBeatRepository extends JpaRepository<HeartBeat, Long> {
    Page<HeartBeat> findByAnimal_Id(Long animalId,
                                          Pageable pageable);
    @Query(value = "SELECT AVG(heart_beat_rate) FROM heart_beat " +
            "WHERE DATE(recorded_at) = CURRENT_DATE AND animal_id = :animalId",
            nativeQuery = true)
    int getAverageHeartBeatRate(@Param("animalId") Long animalId);

    @Query(value = "SELECT DATE(recorded_at), AVG(heart_beat_rate) " +
            "FROM heart_beat " +
            "WHERE recorded_at >= CURRENT_DATE - INTERVAL '6 days' AND animal_id = :animalId " +
            "GROUP BY DATE(recorded_at) " +
            "ORDER BY DATE(recorded_at)",
            nativeQuery = true)
    List<Object[]> findLastSevenDaysAverageHeartBeatCounts(@Param("animalId") Long animalId);


}
