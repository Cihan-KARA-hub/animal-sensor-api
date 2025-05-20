package com.yelman.cloudserver.api;

import com.itextpdf.text.DocumentException;
import com.yelman.cloudserver.api.dto.CompanyDto;
import com.yelman.cloudserver.services.impl.AnimalHealthRuntimeImpl;
import com.yelman.cloudserver.services.impl.CompanyImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:50140")
@RestController
@RequestMapping("api/v1/company")
public class CompanyController {

    private final CompanyImpl companyServices;
    private final AnimalHealthRuntimeImpl animalHealthRuntimeServices;

    public CompanyController(CompanyImpl companyServices, AnimalHealthRuntimeImpl animalHealthRuntimeServices) {
        this.companyServices = companyServices;
        this.animalHealthRuntimeServices = animalHealthRuntimeServices;
    }

    @PostMapping("/add")
    public HttpStatus addCompany(@RequestBody CompanyDto company) {
        boolean result = companyServices.addCompany(company);
        if (result) return HttpStatus.CREATED;
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @GetMapping("/post-pdf/{company_id}")
    public ResponseEntity<Void> getPostPdf(@PathVariable long company_id) throws DocumentException, IOException {
        animalHealthRuntimeServices.weeklyAndDailyPdfMailLogic(company_id, true);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/post-pdf-daily/{company_id}")
    public ResponseEntity<Void> getPostDailyPdf(@PathVariable long company_id) throws DocumentException, IOException {
        animalHealthRuntimeServices.weeklyAndDailyPdfMailLogic(company_id, false);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyServices.deleteCompany(id);
        return ResponseEntity.ok().build();
    }

}
