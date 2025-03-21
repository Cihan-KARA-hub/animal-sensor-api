package com.yelman.cloudserver.services;

import com.yelman.cloudserver.api.dto.VetDto;
import com.yelman.cloudserver.model.Company;
import com.yelman.cloudserver.model.Users;
import com.yelman.cloudserver.model.Vet;
import com.yelman.cloudserver.repository.CompanyRepository;
import com.yelman.cloudserver.repository.UserRepository;
import com.yelman.cloudserver.repository.VetRepository;
import com.yelman.cloudserver.services.impl.VetServicesImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VetServices implements VetServicesImpl {
    private final VetRepository vetRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public VetServices(VetRepository vetRepository, CompanyRepository companyRepository, UserRepository userRepository) {
        this.vetRepository = vetRepository;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Vet getVet(Long id) {
        return null;
    }

    @Override
    public List<Vet> getAllVet() {
        return List.of();
    }

    @Override
    public List<Company> getCompany(Long id) {
        return List.of();
    }

    @Override
    public boolean addVet(VetDto vet) {
        Vet vet1 = vetRepository.save(mapToVet(vet));
        return vet1.getId() != null;
    }


    @Override
    public String updateVet(Vet vet) {
        return "";
    }

    @Override
    public String deleteVet(Long id) {
        return "";
    }


    private Vet mapToVet(VetDto vetDto) {
        Vet vet1 = new Vet();
        vet1.setName(vetDto.getName());
        vet1.setTitle(vetDto.getTitle());
        Optional<Users> user = userRepository.findById(vetDto.getUserId());
        Optional<Company> company = companyRepository.findById(vetDto.getCompanyId());
        vet1.setUser(user.get());
        vet1.setResponsibleCompany(company.get());
        return vet1;
    }
}
