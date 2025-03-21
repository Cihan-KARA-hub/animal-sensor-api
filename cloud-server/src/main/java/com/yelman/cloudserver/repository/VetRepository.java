package com.yelman.cloudserver.repository;

import com.yelman.cloudserver.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetRepository extends JpaRepository<Vet, Long> {
}
