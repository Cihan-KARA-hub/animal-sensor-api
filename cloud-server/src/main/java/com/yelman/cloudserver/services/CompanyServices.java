package com.yelman.cloudserver.services;

import com.yelman.cloudserver.api.dto.CompanyDto;
import com.yelman.cloudserver.model.Company;
import com.yelman.cloudserver.repository.CompanyRepository;
import com.yelman.cloudserver.repository.UserRepository;
import com.yelman.cloudserver.services.impl.CompanyImpl;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class CompanyServices implements CompanyImpl {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public CompanyServices(CompanyRepository companyRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean addCompany(CompanyDto companyDto) {

        Company c = companyRepository.save(mapToCompany(companyDto));
        return c.getId() != null;

    }

    @Override
    public List<Long> getAllCompanyId() {
        return companyRepository.findAllCompanyId();
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    private Company mapToCompany(CompanyDto companyDto) {
        Company c = new Company();
        c.setName(companyDto.getName());
        userRepository.findById(companyDto.getUserId()).ifPresent(c::setUser);
        c.setCreatedAt(OffsetDateTime.now());
        c.setUpdatedAt(OffsetDateTime.now());
        return c;
    }


}
