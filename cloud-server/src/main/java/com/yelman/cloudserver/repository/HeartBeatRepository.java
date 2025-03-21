package com.yelman.cloudserver.repository;

import com.yelman.cloudserver.model.HeartBeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HeartBeatRepository extends JpaRepository<HeartBeat, Long> {
}
