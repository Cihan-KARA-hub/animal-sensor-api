package com.yelman.cloudserver.services.impl;

import com.yelman.cloudserver.api.dto.PdfSensorDto;
import com.yelman.cloudserver.api.dto.SensorDto;

import java.io.IOException;

public interface FuzzyLogicServiceImpl {

    void fuzzyLogicRealtime(SensorDto sensorDto) throws IOException;

    void dailyFuzzyLogic(Long animalId) throws IOException;

    PdfSensorDto weeklyPdfMailLogic(Long animalId);
    PdfSensorDto dailyPdfMailLogic(Long animalId);

}
