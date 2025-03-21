package com.yelman.cloudserver.services.impl;

import com.yelman.cloudserver.api.dto.VetDto;
import com.yelman.cloudserver.model.Company;
import com.yelman.cloudserver.model.Vet;

import java.util.List;

public interface VetServicesImpl {


    Vet getVet(Long id);

    List<Vet> getAllVet();

    List<Company> getCompany(Long id);

    boolean addVet(VetDto vet);

    String updateVet(Vet vet);

    String deleteVet(Long id);

}
