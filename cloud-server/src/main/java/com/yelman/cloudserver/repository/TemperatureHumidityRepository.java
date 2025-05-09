package com.yelman.cloudserver.repository;

import com.yelman.cloudserver.model.TemperatureHumidity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface TemperatureHumidityRepository extends JpaRepository<TemperatureHumidity, Long> {
    Page<TemperatureHumidity> findByAnimal_Id(Long animalId,
                                              Pageable pageable);

    @Query("SELECT AVG(c.temperature) FROM TemperatureHumidity c" +
            " WHERE FUNCTION('DATE', c.recordedAt) = CURRENT_DATE  AND c.animal.id = :animalId")
    Double getAverageTemperature(@Param("animalId") Long animalId);

    @Query("SELECT AVG(c.humidity) FROM TemperatureHumidity c" +
            " WHERE FUNCTION('DATE', c.recordedAt) = CURRENT_DATE  AND c.animal.id = :animalId")
    Double getAverageHumidity(@Param("animalId") Long animalId);

    @Query("SELECT AVG(c.temperature) FROM TemperatureHumidity c WHERE c.recordedAt BETWEEN :start AND :end AND c.animal.id = :animalId")
    Double findAllByRecordedAtBetweens(@Param("start") OffsetDateTime start,
                                        @Param("end") OffsetDateTime end,
                                        @Param("animalId") Long animalId);

    @Query(value = "SELECT DATE(recorded_at), AVG(chew_count) " +
            "FROM temperature_humidity " +
            "WHERE recorded_at >= CURRENT_DATE - INTERVAL '24 hours' AND animal_id = :animalId " +
            "GROUP BY DATE(recorded_at) " +
            "ORDER BY DATE(recorded_at)", nativeQuery = true)
    List<Object[]> findLastDayAverageTemperature(@Param("animalId") Long animalId);
}
