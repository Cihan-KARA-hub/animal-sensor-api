package com.yelman.cloudserver.services.impl;

import com.yelman.cloudserver.api.dto.CreateMedicalHistoryDto;
import com.yelman.cloudserver.api.dto.MedicalHistoryDto;
import com.yelman.cloudserver.model.MedicalHistory;

import java.util.List;

public interface MedicalHistoryImp {

    boolean addMedicalHistory(CreateMedicalHistoryDto medicalHistory);
    List<MedicalHistoryDto> getMedicalHistory(String idTag);
    void deleteMedicalHistory(Long id);
}
