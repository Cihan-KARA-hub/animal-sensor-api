package com.yelman.cloudserver.services;

import com.yelman.cloudserver.api.dto.AnimalDto;
import com.yelman.cloudserver.api.dto.SensorDto;
import com.yelman.cloudserver.model.*;
import com.yelman.cloudserver.repository.*;
import com.yelman.cloudserver.services.impl.AnimalImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalServices implements AnimalImpl {
    private final AnimalRepository animalRepository;
    private final CompanyRepository companyRepository;
    private final ChewingActivityRepository chewingActivityRepository;
    private final HeartBeatRepository heartBeatRepository;
    private final TemperatureHumidityRepository temperatureHumidityRepository;
    private final AlertServices alertServices;
    private final VetRepository vetRepository;


    public AnimalServices(AnimalRepository animalRepository, CompanyRepository companyRepository,
                          ChewingActivityRepository chewingActivityRepository, HeartBeatRepository heartBeatRepository,
                          TemperatureHumidityRepository temperatureHumidityRepository, AlertServices alertServices,
                          VetRepository vetRepository) {
        this.animalRepository = animalRepository;
        this.companyRepository = companyRepository;
        this.chewingActivityRepository = chewingActivityRepository;
        this.heartBeatRepository = heartBeatRepository;
        this.temperatureHumidityRepository = temperatureHumidityRepository;
        this.alertServices = alertServices;
        this.vetRepository = vetRepository;
    }

    @Override
    public boolean addAnimal(AnimalDto animal) {
        Animal newAnimal = animalRepository.save(maptoAnimal(animal));
        return newAnimal.getId() != null;
    }

    @Override
    public List<AnimalDto> getAnimals(Long company) {
        List<Animal> entity = animalRepository.findByCompany_Id(company);
        List<AnimalDto> dto = EntityToDto(entity);
        if (entity.isEmpty()) {
            return null;
        }
        return dto;
    }


    // saattlik post  günlük post olacak
    @Override
    @Transactional
    public boolean postSensor(SensorDto dto, boolean dailyOrHourly) {
        Optional<Animal> animalOptional = animalRepository.findById(dto.getAnimalId());
        if (animalOptional.isEmpty()) {
            return false;
        }

        Animal animal = animalOptional.get();
        boolean heartBeatSaved = saveHeartBeat(dto, animal);
        boolean temperatureHumiditySaved = saveTemperatureHumidity(dto, animal);
        boolean chewingActivitySaved = saveChewingActivity(dto, animal);
        Vet email = vetRepository.findByResponsibleCompany(
                animalOptional.get().getCompany()).get();

        alertServices.emailManager(email, dto, dailyOrHourly);
        return heartBeatSaved || temperatureHumiditySaved || chewingActivitySaved;
    }

    private boolean saveHeartBeat(SensorDto dto, Animal animal) {
        if (dto.getHeartBeat() == null) {
            return false;
        }
        HeartBeat heartBeat = new HeartBeat();
        heartBeat.setAnimal(animal);
        heartBeat.setHeartBeatRate(dto.getHeartBeat());
        heartBeatRepository.save(heartBeat);
        return true;
    }

    private boolean saveTemperatureHumidity(SensorDto dto, Animal animal) {
        if (dto.getTemperatureHumidity() == null || dto.getHumidity() == null) {
            return false;
        }
        TemperatureHumidity temperatureHumidity = new TemperatureHumidity();
        temperatureHumidity.setAnimal(animal);
        temperatureHumidity.setTemperature(dto.getTemperatureHumidity());
        temperatureHumidity.setHumidity(dto.getHumidity());
        temperatureHumidityRepository.save(temperatureHumidity);
        return true;
    }

    private boolean saveChewingActivity(SensorDto dto, Animal animal) {
        if (dto.getChewingActivity() == null) {
            return false;
        }
        ChewingActivity chewingActivity = new ChewingActivity();
        chewingActivity.setAnimal(animal);
        chewingActivity.setChewCount(dto.getChewingActivity());
        chewingActivityRepository.save(chewingActivity);
        return true;
    }

    // dto to entity mapper
    private Animal maptoAnimal(AnimalDto animalDto) {
        Animal newAnimal = new Animal();
        newAnimal.setBirthDate(animalDto.getBirthDate());
        newAnimal.setSpecies(animalDto.getSpecies());
        newAnimal.setTagId(animalDto.getTagId());
        Company c = companyRepository.getById(animalDto.getCompany());
        newAnimal.setCompany(c);
        return newAnimal;
    }

    // entity list  to dto list  mapper
    private List<AnimalDto> EntityToDto(List<Animal> entity) {

        List<AnimalDto> dtos = new ArrayList<>();
        for (Animal animal : entity) {
            AnimalDto dto = new AnimalDto();
            dto.setBirthDate(animal.getBirthDate());
            dto.setTagId(animal.getTagId());
            dto.setSpecies(animal.getSpecies());
            dtos.add(dto);
        }
        return dtos;
    }


}
