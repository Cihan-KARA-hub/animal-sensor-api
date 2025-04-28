package com.yelman.cloudserver.services.impl;

import com.yelman.cloudserver.api.dto.AnimalHealthDto;
import com.yelman.cloudserver.api.dto.SensorDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnimalHealthRuntimeImpl {

    AnimalHealthDto getAnimals(Long animalId, Pageable pageable);

    boolean addAnimalHealthHourlyRuntime(SensorDto animal);

}
