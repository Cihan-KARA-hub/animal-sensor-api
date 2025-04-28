package com.yelman.cloudserver.services;

import com.yelman.cloudserver.api.dto.AnimalHealthDto;
import com.yelman.cloudserver.api.dto.SensorDto;
import com.yelman.cloudserver.model.Animal;
import com.yelman.cloudserver.model.ChewingActivity;
import com.yelman.cloudserver.model.HeartBeat;
import com.yelman.cloudserver.model.TemperatureHumidity;
import com.yelman.cloudserver.repository.AnimalRepository;
import com.yelman.cloudserver.repository.ChewingActivityRepository;
import com.yelman.cloudserver.repository.HeartBeatRepository;
import com.yelman.cloudserver.repository.TemperatureHumidityRepository;
import com.yelman.cloudserver.services.impl.AnimalHealthRuntimeImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimalHealthRuntimeServices implements AnimalHealthRuntimeImpl {

    private final ChewingActivityRepository chewingActivityRepository;
    private final HeartBeatRepository heartBeatRepository;
    private final AnimalRepository animalRepository;
    private final TemperatureHumidityRepository temperatureHumidityRepositoryRepository;

    public AnimalHealthRuntimeServices(ChewingActivityRepository chewingActivityRepository, HeartBeatRepository heartBeatRepository, AnimalRepository animalRepository, TemperatureHumidityRepository temperatureHumidityRepositoryRepository) {
        this.chewingActivityRepository = chewingActivityRepository;
        this.heartBeatRepository = heartBeatRepository;
        this.animalRepository = animalRepository;
        this.temperatureHumidityRepositoryRepository = temperatureHumidityRepositoryRepository;
    }

    @Override
    public AnimalHealthDto getAnimals(Long animalId, Pageable pageable) {

        Page<HeartBeat> h = heartBeatRepository.findByAnimal_Id(animalId, pageable);
        Page<TemperatureHumidity> a = temperatureHumidityRepositoryRepository.findByAnimal_Id(animalId, pageable);
        Page<ChewingActivity> c = chewingActivityRepository.findByAnimal_Id(animalId, pageable);

        AnimalHealthDto animalHealthDto = new AnimalHealthDto();
        animalHealthDto.setHeartBeats(h);
        animalHealthDto.setTemperatureHumidities(a);
        animalHealthDto.setChewingActivities(c);
        return animalHealthDto;

    }

    @Override
    public boolean addAnimalHealthHourlyRuntime(SensorDto animal) {
        boolean a = addChewingActivity(animal.getAnimalId(), animal.getChewingActivity());
        boolean b = addHeartBeat(animal.getAnimalId(), animal.getHeartBeat());
        boolean c = addTemperatureHumidity(animal.getAnimalId(), animal.getTemperatureHumidity(), animal.getHumidity());
        if (a || b || c) {
            return true;
        }
        return false;
    }


    private boolean addChewingActivity(Long animalId, int chewCount) {
        ChewingActivity chewingActivity = new ChewingActivity();
        chewingActivity.setChewCount(chewCount);
        chewingActivity.setAnimal(getByAnimal(animalId).get());
        if (chewingActivity.getAnimal() == null) return false;

        chewingActivityRepository.save(chewingActivity);
        return true;
    }

    private boolean addHeartBeat(Long animalId, int heartBeatCount) {
        HeartBeat heartBeat = new HeartBeat();
        heartBeat.setHeartBeatRate(heartBeatCount);
        heartBeat.setAnimal(getByAnimal(animalId).get());
        if (heartBeat.getAnimal() == null) return false;
        heartBeatRepository.save(heartBeat);
        return true;
    }

    private boolean addTemperatureHumidity(Long animalId, double temperature, double humidity) {
        TemperatureHumidity temperatureHumidity = new TemperatureHumidity();
        temperatureHumidity.setHumidity(humidity);
        temperatureHumidity.setAnimal(getByAnimal(animalId).get());
        if (temperatureHumidity.getAnimal() == null) return false;
        temperatureHumidity.setTemperature(temperature);
        temperatureHumidityRepositoryRepository.save(temperatureHumidity);
        return true;
    }

    private Optional<Animal> getByAnimal(Long animalId) {
        Optional<Animal> animal = animalRepository.findById(animalId);
        if (animal.isPresent()) {
            return animal;
        }
        return null;
    }
}
