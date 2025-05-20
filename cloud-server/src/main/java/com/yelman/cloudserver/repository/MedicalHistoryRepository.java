package com.yelman.cloudserver.repository;

import com.yelman.cloudserver.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
List<MedicalHistory> findByAnimal_TagId(String animal_tagId);
void deleteByAnimalId(Long animalId);
}
