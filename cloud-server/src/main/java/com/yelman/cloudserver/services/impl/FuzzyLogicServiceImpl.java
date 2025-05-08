package com.yelman.cloudserver.services.impl;

import com.yelman.cloudserver.api.dto.PdfSensorDto;
import com.yelman.cloudserver.api.dto.SensorDto;

public interface FuzzyLogicServiceImpl {

    void fuzzyLogicRealtime(SensorDto sensorDto);
     void dailyFuzzyLogic(Long animalId);
    PdfSensorDto weeklyPdfMailLogic(Long animalId);

}
