package com.yelman.cloudserver.services;

import com.yelman.cloudserver.api.dto.AnimalDto;
import com.yelman.cloudserver.model.Animal;
import com.yelman.cloudserver.model.Company;
import com.yelman.cloudserver.repository.AnimalRepository;
import com.yelman.cloudserver.repository.CompanyRepository;
import com.yelman.cloudserver.services.impl.AnimalImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalServices implements AnimalImpl {
    private final AnimalRepository animalRepository;
    private final CompanyRepository companyRepository;

    public AnimalServices(AnimalRepository animalRepository, CompanyRepository companyRepository) {
        this.animalRepository = animalRepository;
        this.companyRepository = companyRepository;
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
        // ilgili  verileri de sil
        animalRepository.delete(animal);
    }

    private Animal maptoAnimal(AnimalDto animalDto) {
        Animal newAnimal = new Animal();
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
            dto.setBirthDate(animal.getBirthDate());
            dto.setTagId(animal.getTagId());
            dto.setSpecies(animal.getSpecies());
            dtos.add(dto);
        }
        return dtos;
    }


}
