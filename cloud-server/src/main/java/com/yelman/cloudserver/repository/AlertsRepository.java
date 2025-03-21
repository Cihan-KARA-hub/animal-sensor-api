package com.yelman.cloudserver.repository;

import com.yelman.cloudserver.model.Alerts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertsRepository extends JpaRepository<Alerts, Long> {
}
