package com.yelman.cloudserver.services.impl;

import com.itextpdf.text.DocumentException;
import com.yelman.cloudserver.api.dto.AnimalHealthDto;
import com.yelman.cloudserver.api.dto.SensorDto;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface AnimalHealthRuntimeImpl {

    AnimalHealthDto getAnimals(String animalTagId, Pageable pageable);

    boolean addAnimalHealthHourlyRuntime(SensorDto animal) throws IOException;

    void deleteAllSensorAnimalId(Long animalId);

    void weeklyAndDailyPdfMailLogic(Long companyId,boolean weekIsDay) throws DocumentException, IOException;

}
