package com.yelman.cloudserver.services.impl;

import com.itextpdf.text.DocumentException;
import com.yelman.cloudserver.api.dto.AnimalHealthDto;
import com.yelman.cloudserver.api.dto.SensorDto;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface AnimalHealthRuntimeImpl {

    AnimalHealthDto getAnimals(Long animalId, Pageable pageable);

    boolean addAnimalHealthHourlyRuntime(SensorDto animal);

    void deleteAllSensorAnimalId(Long animalId);

    void weeklyPdfMailLogic(Long companyId) throws DocumentException, IOException;

}
