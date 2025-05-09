package com.yelman.cloudserver.services.core;

import com.yelman.cloudserver.api.dto.AlertDto;
import com.yelman.cloudserver.api.dto.AlertRealtimeDto;
import com.yelman.cloudserver.api.dto.PdfSensorDto;
import com.yelman.cloudserver.api.dto.SensorDto;
import com.yelman.cloudserver.model.Animal;
import com.yelman.cloudserver.model.Vet;
import com.yelman.cloudserver.repository.*;
import com.yelman.cloudserver.services.impl.AlertsImpl;
import com.yelman.cloudserver.services.impl.FuzzyLogicServiceImpl;
import com.yelman.cloudserver.utils.fuzzy.HealthCheck;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class FuzzyLogicService implements FuzzyLogicServiceImpl {
    private final AnimalRepository animalRepository;
    private final VetRepository vetRepository;
    private final AlertsImpl alertServices;
    private final ChewingActivityRepository chewingActivityRepository;
    private final HeartBeatRepository heartBeatRepository;
    private final TemperatureHumidityRepository temperatureHumidityRepository;

    public FuzzyLogicService(AnimalRepository animalRepository, VetRepository vetRepository, AlertsImpl alertServices, ChewingActivityRepository chewingActivityRepository, HeartBeatRepository heartBeatRepository, TemperatureHumidityRepository temperatureHumidityRepository) {
        this.animalRepository = animalRepository;
        this.vetRepository = vetRepository;
        this.alertServices = alertServices;
        this.chewingActivityRepository = chewingActivityRepository;
        this.heartBeatRepository = heartBeatRepository;
        this.temperatureHumidityRepository = temperatureHumidityRepository;
    }

    @Override
    public void fuzzyLogicRealtime(SensorDto sensorDto) {
        try {
            Vet vet = findByVet(sensorDto.getAnimalId());
            AlertRealtimeDto dtos = new AlertRealtimeDto(sensorDto.getTemperature(), sensorDto.getHeartBeat(), sensorDto.getHumidity());
            Double a = HealthCheck.fuzzyLogicRealtime(dtos);
            alertServices.emailManager(vet, sensorDto, true, a.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dailyFuzzyLogic(Long animalId) throws IOException {
        Vet vet = findByVet(animalId);
        SensorDto sensorDto = new SensorDto();
        sensorDto.setChewingActivity(chewingActivityRepository.findTodayAverageChewingCount(animalId));
        sensorDto.setHeartBeat(heartBeatRepository.getAverageHeartBeatRate(animalId));
        sensorDto.setTemperature(temperatureHumidityRepository.getAverageTemperature(animalId));
        sensorDto.setHumidity(temperatureHumidityRepository.getAverageHumidity(animalId));
        AlertDto dtos = new AlertDto(sensorDto.getTemperature(), sensorDto.getHeartBeat(),(Integer) sensorDto.getChewingActivity(), sensorDto.getHumidity());
        Double a = HealthCheck.fuzzyLogicGeneral(dtos);
        alertServices.emailManager(vet, sensorDto, true, a.toString());
    }
    @Override
    public PdfSensorDto weeklyPdfMailLogic(Long animalId) {
        Animal animal = animalRepository.findById(animalId).orElseThrow();
        PdfSensorDto sensorDto = new PdfSensorDto();
        sensorDto.setChewingActivity(chewingActivityRepository.findAllByRecordedAtBetweens(OffsetDateTime.now().minusDays(7), OffsetDateTime.now(), animalId));
        sensorDto.setDate("Start Date : " + Date.from(Instant.now()) + "\n" + "End Date : " + Date.from(Instant.now().minus(7, ChronoUnit.DAYS)));
        sensorDto.setTemperature(temperatureHumidityRepository.findAllByRecordedAtBetweens(OffsetDateTime.now().minusDays(7), OffsetDateTime.now(), animalId));
        sensorDto.setHeartBeat(heartBeatRepository.findAverageHeartBeatRateByRecordedAtBetweenAndAnimalId(OffsetDateTime.now().minusDays(7), OffsetDateTime.now(), animalId));
        sensorDto.setTag_id("Küpe No: " + animal.getTagId());
        sensorDto.setHumidity(75.0);
        AlertDto dtos = new AlertDto(sensorDto.getTemperature(), sensorDto.getHeartBeat(), sensorDto.getChewingActivity(), sensorDto.getHumidity());
        Double a = HealthCheck.fuzzyLogicGeneral(dtos);
        sensorDto.setRiskScore(a);
        return sensorDto;
    }
    @Override
    public  PdfSensorDto dailyPdfMailLogic(Long animalId){
        Animal animal = animalRepository.findById(animalId).orElseThrow();
        PdfSensorDto sensorDto = new PdfSensorDto();
        sensorDto.setChewingActivity(chewingActivityRepository.findAllByRecordedAtBetweens(OffsetDateTime.now().minusDays(1), OffsetDateTime.now(), animalId));
        sensorDto.setDate("Start Date : " + Date.from(Instant.now()) + "\n" + "End Date : " + Date.from(Instant.now().minus(7, ChronoUnit.DAYS)));
        sensorDto.setTemperature(temperatureHumidityRepository.findAllByRecordedAtBetweens(OffsetDateTime.now().minusDays(1), OffsetDateTime.now(), animalId));
        sensorDto.setHeartBeat(heartBeatRepository.findAverageHeartBeatRateByRecordedAtBetweenAndAnimalId(OffsetDateTime.now().minusDays(1), OffsetDateTime.now(), animalId));
        sensorDto.setTag_id("Küpe No: " + animal.getTagId());
        sensorDto.setHumidity(75.0);
        AlertDto alertDto = new AlertDto(sensorDto.getTemperature(), sensorDto.getHeartBeat(), sensorDto.getChewingActivity(), sensorDto.getHumidity());
        Double a = HealthCheck.fuzzyLogicGeneral(alertDto);
        sensorDto.setRiskScore(a);
        return sensorDto;
    }

    private Vet findByVet(Long animalId) {
        Animal animal = animalRepository.findById(animalId).orElseThrow();
        return vetRepository.findByResponsibleCompany(animal.getCompany());
    }

}
