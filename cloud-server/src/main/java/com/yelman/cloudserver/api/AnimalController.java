package com.yelman.cloudserver.api;

import com.yelman.cloudserver.api.dto.AnimalDto;
import com.yelman.cloudserver.api.dto.CreateMedicalHistoryDto;
import com.yelman.cloudserver.api.dto.MedicalHistoryDto;
import com.yelman.cloudserver.services.impl.AnimalImpl;
import com.yelman.cloudserver.services.impl.MedicalHistoryImp;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:50140")
@RestController
@RequestMapping("api/v1/animal")
public class AnimalController {

    private final AnimalImpl animalImpl;
    private final MedicalHistoryImp medicalHistoryImp;

    public AnimalController(AnimalImpl animalImpl, MedicalHistoryImp medicalHistoryImp) {

        this.animalImpl = animalImpl;

        this.medicalHistoryImp = medicalHistoryImp;
    }

    @PostMapping("")
    public HttpStatus create(@RequestBody AnimalDto animal) {
        boolean a = animalImpl.addAnimal(animal);
        if (a) {
            return HttpStatus.CREATED;
        }
        return HttpStatus.BAD_REQUEST;
    }

    @PostMapping("/add-medical-history")
    public HttpStatus addMedicalHistory(@RequestBody CreateMedicalHistoryDto medicalHistory) {
        boolean a = medicalHistoryImp.addMedicalHistory(medicalHistory);
        if (a) {
            return HttpStatus.CREATED;
        }
        return HttpStatus.BAD_REQUEST;
    }

    @GetMapping("/company/{id}")
    public List<AnimalDto> getAnimalCompanyById(@PathVariable Long id) {
        return animalImpl.getAnimals(id);

    }

    @PutMapping("update/{id}")
    public HttpStatus updateAnimal(@PathVariable Long id, @RequestBody AnimalDto animal) {
        boolean a = animalImpl.update(id, animal);
        if (a) {
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteAnimal(@PathVariable Long id) {
        boolean a = animalImpl.deleteAnimal(id);
        if (a) {
            return HttpStatus.OK;
        }
        return HttpStatus.NO_CONTENT;
    }

}
