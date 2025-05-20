package com.yelman.cloudserver.services.impl;

import com.yelman.cloudserver.api.dto.AnimalDto;
import com.yelman.cloudserver.api.dto.SensorDto;
import com.yelman.cloudserver.model.Animal;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface AnimalImpl {
    boolean addAnimal(AnimalDto animal);

    List<AnimalDto> getAnimals(Long company);
    boolean deleteAnimal(Long id);

    boolean update(Long id, AnimalDto animal);
}
