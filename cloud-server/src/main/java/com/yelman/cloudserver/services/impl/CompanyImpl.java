package com.yelman.cloudserver.services.impl;

import com.yelman.cloudserver.api.dto.CompanyDto;

import java.util.List;

public interface CompanyImpl {

    Boolean addCompany(CompanyDto company);
    List<Long> getAllCompanyId();
    void deleteCompany(Long id);
}
