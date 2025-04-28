package com.yelman.cloudserver.services.impl;

import com.yelman.cloudserver.api.dto.MedicalHistoryDto;
import com.yelman.cloudserver.model.MedicalHistory;

import java.util.List;

public interface MedicalHistoryImp {

    boolean addMedicalHistory(MedicalHistoryDto medicalHistory);
    List<MedicalHistoryDto> getMedicalHistory(long id);
}
