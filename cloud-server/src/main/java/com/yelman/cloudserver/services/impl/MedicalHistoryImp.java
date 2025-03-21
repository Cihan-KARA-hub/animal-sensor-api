package com.yelman.cloudserver.services.impl;

import com.yelman.cloudserver.api.dto.MedicalHistoryDto;
import com.yelman.cloudserver.model.MedicalHistory;

public interface MedicalHistoryImp {

    boolean addMedicalHistory(MedicalHistoryDto medicalHistory);
}
