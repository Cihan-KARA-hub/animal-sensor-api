package com.yelman.cloudserver.services;

import com.yelman.cloudserver.api.dto.CreateMedicalHistoryDto;
import com.yelman.cloudserver.api.dto.MedicalHistoryDto;
import com.yelman.cloudserver.model.MedicalHistory;
import com.yelman.cloudserver.repository.AnimalRepository;
import com.yelman.cloudserver.repository.MedicalHistoryRepository;
import com.yelman.cloudserver.services.impl.MedicalHistoryImp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalHistoryServices implements MedicalHistoryImp {
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final AnimalRepository animalRepository;

    public MedicalHistoryServices(MedicalHistoryRepository medicalHistoryRepository, AnimalRepository animalRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public boolean addMedicalHistory(CreateMedicalHistoryDto medicalHistory) {
        MedicalHistory medicalHistoryEntity = medicalHistoryRepository.save(mapToCreateEntity(medicalHistory));
        return medicalHistoryEntity.getId() != null;
    }

    @Override
    public List<MedicalHistoryDto> getMedicalHistory(String idTag) {
        List<MedicalHistory> medicalHistories = medicalHistoryRepository.findByAnimal_TagId(idTag);
        return listToDto(medicalHistories);
    }

    @Override
    public void deleteMedicalHistory(Long id) {
        medicalHistoryRepository.deleteById(id);
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
    private MedicalHistory mapToCreateEntity(CreateMedicalHistoryDto medicalHistoryDto) {
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setDiagnosisDate(medicalHistoryDto.getDiagnosisDate());
        medicalHistory.setDiseaseName(medicalHistoryDto.getDiseaseName());
        medicalHistory.setTreatment(medicalHistoryDto.getTreatment());
        medicalHistory.setRecoveryDate(medicalHistoryDto.getRecoveryDate());
        medicalHistory.setVeterinarian(medicalHistoryDto.getVeterinarian());
        medicalHistory.setAnimal(animalRepository.findById(medicalHistoryDto.getAnimalId()).orElse(null));
        return medicalHistory;
    }

    private List<MedicalHistoryDto> listToDto(List<MedicalHistory> medicalHistories) {
        List<MedicalHistoryDto> medicalHistoryDtos = new ArrayList<>();
        medicalHistories.forEach(medicalHistory -> {
            MedicalHistoryDto dto = new MedicalHistoryDto();
            dto.setId(medicalHistory.getId());
            dto.setDiagnosisDate(medicalHistory.getDiagnosisDate());
            dto.setAnimalId(medicalHistory.getAnimal().getId());
            dto.setVeterinarian(medicalHistory.getVeterinarian());
            dto.setTreatment(medicalHistory.getTreatment());
            dto.setRecoveryDate(medicalHistory.getRecoveryDate());
            dto.setDiseaseName(medicalHistory.getDiseaseName());
            medicalHistoryDtos.add(dto);
        });
        return medicalHistoryDtos;
    }
}
