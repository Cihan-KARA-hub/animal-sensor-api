package com.yelman.cloudserver.api;

import com.yelman.cloudserver.api.dto.AnimalDto;
import com.yelman.cloudserver.api.dto.MedicalHistoryDto;
import com.yelman.cloudserver.api.dto.SensorDto;
import com.yelman.cloudserver.services.AnimalServices;
import com.yelman.cloudserver.services.MedicalHistoryServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:56038")
@RestController
@RequestMapping("api/v1/animal")
public class AnimalController {
    private final AnimalServices animalService;
    private final MedicalHistoryServices medicalHistoryService;

    public AnimalController(AnimalServices animalService, MedicalHistoryServices medicalHistoryService) {
        this.animalService = animalService;
        this.medicalHistoryService = medicalHistoryService;
    }

    @PostMapping("")
    public HttpStatus create(@RequestBody AnimalDto animal) {
        boolean a = animalService.addAnimal(animal);
        if (a) {
            return HttpStatus.CREATED;
        }
        return HttpStatus.BAD_REQUEST;
    }

    @PostMapping("/add-medical-history")
    public HttpStatus addMedicalHistory(@RequestBody MedicalHistoryDto medicalHistory) {
        boolean a = medicalHistoryService.addMedicalHistory(medicalHistory);
        if (a) {
            return HttpStatus.CREATED;
        }
        return HttpStatus.BAD_REQUEST;
    }

    // TODO :: verilen çiftlikteki  hayvanları sırala
    @GetMapping("/company/{id}")
    public List<AnimalDto> getAnimalCompanyById(@PathVariable Long id) {
        return animalService.getAnimals(id);

    }

    @PostMapping("/hourly-sensor")
    public HttpStatus postHourlySensor(@RequestBody SensorDto sensors) {
        boolean a = animalService.postSensor(sensors, true);
        if (a) {
            return HttpStatus.CREATED;
        }
        return HttpStatus.BAD_REQUEST;
    }


}
