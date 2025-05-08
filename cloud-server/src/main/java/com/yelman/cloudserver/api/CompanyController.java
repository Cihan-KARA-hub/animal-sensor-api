package com.yelman.cloudserver.api;

import com.itextpdf.text.DocumentException;
import com.yelman.cloudserver.api.dto.CompanyDto;
import com.yelman.cloudserver.services.AnimalHealthRuntimeServices;
import com.yelman.cloudserver.services.CompanyServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/company")
public class CompanyController {

    private final CompanyServices companyServices;
    private final AnimalHealthRuntimeServices animalHealthRuntimeServices;

    public CompanyController(CompanyServices companyServices, AnimalHealthRuntimeServices animalHealthRuntimeServices) {
        this.companyServices = companyServices;
        this.animalHealthRuntimeServices = animalHealthRuntimeServices;
    }

    @PostMapping("/ada")
    public HttpStatus addCompany(@RequestBody CompanyDto company) {
        boolean result = companyServices.addCompany(company);
        if (result) return HttpStatus.CREATED;
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @GetMapping("/post-pdf/{company_id}")
    public ResponseEntity<Void> getPostPdf(@PathVariable long company_id) throws DocumentException, IOException {
        animalHealthRuntimeServices.weeklyPdfMailLogic(company_id);
        return ResponseEntity.ok().build();
    }

}
