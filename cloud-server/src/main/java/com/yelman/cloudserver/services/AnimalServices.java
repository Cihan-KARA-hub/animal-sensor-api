package com.yelman.cloudserver.services;

import com.yelman.cloudserver.api.dto.AnimalDto;
import com.yelman.cloudserver.model.Animal;
import com.yelman.cloudserver.model.Company;
import com.yelman.cloudserver.repository.AnimalRepository;
import com.yelman.cloudserver.repository.CompanyRepository;
import com.yelman.cloudserver.services.impl.AnimalImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalServices implements AnimalImpl {
    private final AnimalRepository animalRepository;
    private final CompanyRepository companyRepository;
    private final MedicalHistoryServices medicalHistoryServices;
    private final AnimalHealthRuntimeServices animalHealthRuntimeServices;

    public AnimalServices(AnimalRepository animalRepository, CompanyRepository companyRepository, MedicalHistoryServices medicalHistoryServices, AnimalHealthRuntimeServices animalHealthRuntimeServices) {
        this.animalRepository = animalRepository;
        this.companyRepository = companyRepository;
        this.medicalHistoryServices = medicalHistoryServices;
        this.animalHealthRuntimeServices = animalHealthRuntimeServices;
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

    @Override
    public boolean deleteAnimal(Long id) {
        Animal animal = animalRepository.findById(id).orElse(null);
        if (animal == null) {
            return false;
        }
        animalHealthRuntimeServices.deleteAllSensorAnimalId(id);
        medicalHistoryServices.deleteMedicalHistory(id);
        animalRepository.deleteById(animal.getId());
        return true;
    }

    @Override
    @Transactional
    public boolean update(Long id, AnimalDto animal) {
        Animal oldAnimal = animalRepository.findById(id).orElse(null);
        if (oldAnimal == null) {
            return false;
        }
        oldAnimal.setTagId(animal.getTagId());
        oldAnimal.setSpecies(animal.getSpecies());
        oldAnimal.setBirthDate(animal.getBirthDate());
        Animal newAnimal = animalRepository.save(oldAnimal);
        if (newAnimal == null) {
            return false;
        }
        return true;
    }

    private Animal maptoAnimal(AnimalDto animalDto) {
        Animal newAnimal = new Animal();
        newAnimal.setId(animalDto.getId());
        newAnimal.setBirthDate(animalDto.getBirthDate());
        newAnimal.setSpecies(animalDto.getSpecies());
        newAnimal.setTagId(animalDto.getTagId());
        Company c = companyRepository.getById(animalDto.getCompany());
        newAnimal.setCompany(c);
        return newAnimal;
    }

    private List<AnimalDto> EntityToDto(List<Animal> entity) {
        List<AnimalDto> dtos = new ArrayList<>();
        for (Animal animal : entity) {
            AnimalDto dto = new AnimalDto();
            dto.setId(animal.getId());
            dto.setBirthDate(animal.getBirthDate());
            dto.setTagId(animal.getTagId());
            dto.setSpecies(animal.getSpecies());
            dtos.add(dto);
        }
        return dtos;
    }


}
