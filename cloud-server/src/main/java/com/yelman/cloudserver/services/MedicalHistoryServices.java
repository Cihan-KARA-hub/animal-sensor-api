package com.yelman.cloudserver.services;

import com.yelman.cloudserver.api.dto.MedicalHistoryDto;
import com.yelman.cloudserver.model.MedicalHistory;
import com.yelman.cloudserver.repository.AnimalRepository;
import com.yelman.cloudserver.repository.MedicalHistoryRepository;
import com.yelman.cloudserver.services.impl.MedicalHistoryImp;
import org.springframework.stereotype.Service;

@Service
public class MedicalHistoryServices implements MedicalHistoryImp {
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final AnimalRepository animalRepository;

    public MedicalHistoryServices(MedicalHistoryRepository medicalHistoryRepository, AnimalRepository animalRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public boolean addMedicalHistory(MedicalHistoryDto medicalHistory) {

        MedicalHistory medicalHistoryEntity = medicalHistoryRepository.save(mapToEntity(medicalHistory));
        return medicalHistoryEntity.getId() != null;
    }

    private MedicalHistory mapToEntity(MedicalHistoryDto medicalHistoryDto) {
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setDiagnosisDate(medicalHistoryDto.getDiagnosisDate());
        medicalHistory.setDiseaseName(medicalHistoryDto.getDiseaseName());
        medicalHistory.setTreatment(medicalHistoryDto.getTreatment());
        medicalHistory.setRecoveryDate(medicalHistoryDto.getRecoveryDate());
        medicalHistory.setVeterinarian(medicalHistoryDto.getVeterinarian());
        medicalHistory.setAnimal(animalRepository.findById(medicalHistoryDto.getAnimalId()).orElse(null));
        return medicalHistory;
    }

}
