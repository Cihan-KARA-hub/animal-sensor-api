package com.yelman.cloudserver.repository;

import com.yelman.cloudserver.model.ChewingActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface ChewingActivityRepository extends JpaRepository<ChewingActivity, Long> {
    Page<ChewingActivity> findByAnimal_Id(Long animalId,
                                          Pageable pageable);

    @Query("SELECT AVG(c.chewCount) FROM ChewingActivity c " +
            "WHERE FUNCTION('DATE', c.recordedAt) = CURRENT_DATE AND c.animal.id = :animalId")
    int findTodayAverageChewingCount(@Param("animalId") Long animalId);


    @Query("SELECT AVG(c.chewCount) FROM ChewingActivity c WHERE c.recordedAt BETWEEN :start AND :end AND c.animal.id = :animalId")
    Double findAllByRecordedAtBetweens(@Param("start") OffsetDateTime start,
                                        @Param("end") OffsetDateTime end,
                                        @Param("animalId") Long animalId);

    @Query(value = "SELECT DATE(recorded_at), AVG(chew_count) " +
            "FROM chewing_activity " +
            "WHERE recorded_at >= CURRENT_DATE - INTERVAL '24 hours' AND animal_id = :animalId " +
            "GROUP BY DATE(recorded_at) " +
            "ORDER BY DATE(recorded_at)", nativeQuery = true)
    List<Object[]> findLastDayAverageChewingCounts(@Param("animalId") Long animalId);


}
