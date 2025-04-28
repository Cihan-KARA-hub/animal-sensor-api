package com.yelman.cloudserver.services.impl;

import com.yelman.cloudserver.api.dto.AnimalDto;
import com.yelman.cloudserver.api.dto.SensorDto;
import com.yelman.cloudserver.model.Animal;

import java.util.List;

public interface AnimalImpl {
    boolean addAnimal(AnimalDto animal);

    List<AnimalDto> getAnimals(Long company);

    boolean postSensor(SensorDto dto, boolean dailyOrHourly);
}
