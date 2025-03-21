package com.yelman.cloudserver.api;

import com.yelman.cloudserver.api.dto.CompanyDto;
import com.yelman.cloudserver.services.CompanyServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/company")
public class CompanyController {

    private final CompanyServices companyServices;

    public CompanyController(CompanyServices companyServices) {
        this.companyServices = companyServices;
    }

    @PostMapping("/ada")
    public HttpStatus addCompany(@RequestBody CompanyDto company) {
        boolean result = companyServices.addCompany(company);
        if (result) return HttpStatus.CREATED;
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
